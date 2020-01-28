package com.example.kpsktmhelpdesk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Appstatus {

    private static Appstatus appstatus = new Appstatus();
    Context cxt;
    private ConnectivityManager connectivityManager;
    private boolean connected =  false;

    public static Appstatus getInstance(Context cxt){
        cxt = cxt.getApplicationContext();
        return appstatus;
    }

    public boolean isOnline(){
        connectivityManager = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        return connected;
    }


}
