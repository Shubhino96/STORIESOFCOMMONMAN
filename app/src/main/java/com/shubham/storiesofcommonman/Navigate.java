package com.shubham.storiesofcommonman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubham.storiesofcommonman.MainActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class Navigate extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{


    private GoogleApiClient mGoogleApiClient;

    private  NavigationView navigationView;
    private  TextView nav_user;
    private  TextView nav_email;
    private  View hView;
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    ImageView user_picture;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mBlogList = (RecyclerView)findViewById(R.id.blog);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();


        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        String jsonString = intent.getStringExtra("jsonObject");

        if(jsonString != null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(jsonString);
                navigationView = (NavigationView) findViewById(R.id.nav_view);
                hView =  navigationView.getHeaderView(0);

                nav_user = (TextView)hView.findViewById(R.id.nav_name);
                nav_user.setText(jsonObject.getString("name"));

                nav_email = (TextView)hView.findViewById(R.id.textView);
                nav_email.setText(jsonObject.getString("email"));

                user_picture = (ImageView) hView.findViewById(R.id.imageView);

                Picasso.with(this)
                        .load("https://graph.facebook.com/v2.2/" + jsonObject.getString("id")+ "/picture?height=120&type=normal").transform(new RoundedTransformation(50, 4)) //extract as User instance method
                        .resize(120,120)
                        .into(user_picture);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            String name = extras.getString("name");
            String email = extras.getString("email");

            Uri pic = extras.getParcelable("pic");

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            hView =  navigationView.getHeaderView(0);

            nav_user = (TextView)hView.findViewById(R.id.nav_name);
            nav_user.setText(name);

            nav_email = (TextView)hView.findViewById(R.id.textView);
            nav_email.setText(email);

            user_picture = (ImageView) hView.findViewById(R.id.imageView);

            Picasso.with(this).load(pic).transform(new RoundedTransformation(50, 4)).resize(120,120).into(user_picture);

        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class RoundedTransformation implements com.squareup.picasso.Transformation
    {
        private final int radius;
        private final int margin;  // dp

        // radius is corner radii in dp
        // margin is the board in dp
        public RoundedTransformation(final int radius, final int margin) {
            this.radius = radius;
            this.margin = margin;
        }

        @Override
        public Bitmap transform(final Bitmap source)
        {
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

            if (source != output) {
                source.recycle();
            }

            return output;
        }

        @Override
        public String key() {
            return "rounded";
        }
    }


   /* public Bitmap getUserPic(String userID) {
        String imageURL;
        Bitmap bitmap = null;
        imageURL = "http://graph.facebook.com/"+userID+"/picture?type=small";
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
        } catch (Exception e) {
            Log.d("TAG", "Loading Picture FAILED");
            e.printStackTrace();
        }
        return bitmap;
    }*/

    private void googlePlusLogout() {
        if (mGoogleApiClient.isConnected())
        {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);

            mGoogleApiClient.disconnect();

            mGoogleApiClient.connect();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigate, menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        if(item.getItemId() == R.id.action_add)
        {
            Intent i = new Intent(Navigate.this,PostActivity.class);
            Bundle extras = new Bundle();
            extras.putString("name", String.valueOf(nav_user));
            i.putExtras(extras);
            startActivity(i);
        }



        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_upload)
        {
            Intent i = new Intent(Navigate.this,PostActivity.class);
            Bundle extras = new Bundle();
            extras.putString("name", String.valueOf(nav_user));
            i.putExtras(extras);
            startActivity(i);
        }
        else
        if (id == R.id.nav_exit)
        {

            LoginManager.getInstance().logOut();
            googlePlusLogout();
            System.exit(0);

        } else if (id == R.id.nav_slideshow)
        {

        } else if (id == R.id.nav_manage)
        {

        } else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTilte(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());

            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public  static  class BlogViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }

        public  void setTilte(String title)
        {
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc)
        {
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image)
        {
           ImageView post_image = (ImageView)mView.findViewById(R.id.post_image);
           Picasso.with(ctx).load(image).into(post_image);
        }


    }
    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(
                (ResultCallback<? super People.LoadPeopleResult>) this);
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
