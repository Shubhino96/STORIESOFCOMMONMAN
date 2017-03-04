package com.shubham.storiesofcommonman;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class PostActivity extends AppCompatActivity
{

    private ImageButton mSelectImage;

    private  static  final  int GALLERY_REQUEST = 1;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitBtn;
    private  Uri  imageUri = null;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    private  String name;
    private  String email;
    private  String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);



        mSelectImage = (ImageButton) findViewById(R.id.imageSelect);
        mPostTitle = (EditText) findViewById(R.id.titleField);
        mPostDesc = (EditText) findViewById(R.id.descField);
        mSubmitBtn = (Button) findViewById(R.id.submitBtn);
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        name = extras.getString("name");
        email = extras.getString("email");
        mProgress = new ProgressDialog(this);
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);


            }
        });


        mSubmitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startPosting();
            }
        });
    }
        private void startPosting()
       {
           mProgress.setMessage("Posting to Blog..");

           final String title_val = mPostTitle.getText().toString().trim();
           final String desc_val  = mPostDesc.getText().toString().trim();



           if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val)&& imageUri!=null)
           {
               mProgress.show();
               StorageReference filepath = mStorage.child("Blog_Images").child(imageUri.getLastPathSegment());
               filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                   {

                       Uri downloadUrl = taskSnapshot.getDownloadUrl();

                       DatabaseReference newPost = mDatabase.push();
                       newPost.child("title").setValue(title_val);
                       newPost.child("desc").setValue(desc_val);
                       newPost.child("image").setValue(downloadUrl.toString());
                       newPost.child("username").setValue(name);
                       id=  UUID.randomUUID().toString();
                       newPost.child("uid").setValue(id);
                       mProgress.dismiss();
                       finish();

                   }
               });

           }

       }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == GALLERY_REQUEST && resultCode == RESULT_OK)
        {
             imageUri = data.getData();
            mSelectImage.setImageURI(imageUri);

        }
    }
}
