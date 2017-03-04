package com.shubham.storiesofcommonman;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BlogSingleActivity extends AppCompatActivity
{

    private  String post_key =null;
    private DatabaseReference mDatabase;
    private ImageView mBlogImage;
    private TextView  mBlogtitle;
    private TextView  mBlogdesc;
    private String desc;
    private String title;
    private String image;
    private Button mremove;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_single);

        getActionBar();
        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.icon);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference id = mDatabase.child("Blog");
        mBlogdesc = (TextView) findViewById(R.id.desc);
        mBlogImage = (ImageView) findViewById(R.id.BlogImage);
        mBlogtitle = (TextView) findViewById(R.id.title);
        //mremove = (Button) findViewById(R.id.remove);
        post_key = getIntent().getExtras().getString("id");
        desc = getIntent().getExtras().getString("desc");
        title = getIntent().getExtras().getString("title");
        image = getIntent().getExtras().getString("image");
        Toast.makeText(BlogSingleActivity.this, post_key, Toast.LENGTH_LONG).show();
        mBlogdesc.setText(desc);
        mBlogtitle.setText(title);
        Picasso.with(BlogSingleActivity.this).load(image).into(mBlogImage);

    }
}
