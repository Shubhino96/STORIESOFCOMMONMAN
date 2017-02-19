package com.shubham.storiesofcommonman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.TextView;

public class DayNightActivity extends AppCompatActivity
{

     TextView textModeType;
    private int modeType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_night);
        initViews();
    }


    private void initViews() {
        textModeType = (TextView) findViewById(R.id.textviewMain);

        modeType = AppCompatDelegate.getDefaultNightMode();

        if (modeType == AppCompatDelegate.MODE_NIGHT_AUTO) {
            textModeType.setText("Default Mode Type: Auto");
        } else if (modeType == AppCompatDelegate.MODE_NIGHT_NO) {
            textModeType.setText("Default Mode Type: Day");
        } else if (modeType == AppCompatDelegate.MODE_NIGHT_YES) {
            textModeType.setText("Default Mode Type: Night");
        }
    }
}
