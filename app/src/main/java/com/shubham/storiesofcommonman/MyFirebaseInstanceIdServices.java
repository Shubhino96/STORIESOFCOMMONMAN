package com.shubham.storiesofcommonman;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by SHUBHAM on 2/11/2017.
 */

public class MyFirebaseInstanceIdServices extends FirebaseInstanceIdService

{

    private  static final  String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh()
    {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(REG_TOKEN,"Refreshed Token"+refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }


    private void sendRegistrationToServer(String token)
    {
        // TODO: Implement this method to send token to your app server.
    }
}
