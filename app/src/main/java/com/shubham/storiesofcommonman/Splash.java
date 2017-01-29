package com.shubham.storiesofcommonman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by SHUBHAM on 1/28/2017.
 */

public class Splash extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh);

        ImageView i = (ImageView)findViewById(R.id.imageView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
            }
        },1000);
    }
}
