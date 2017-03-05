package com.shubham.storiesofcommonman;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class About extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

    }
}
