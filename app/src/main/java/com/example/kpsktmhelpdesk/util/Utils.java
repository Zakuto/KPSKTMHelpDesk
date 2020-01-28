package com.example.kpsktmhelpdesk.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getCurrentDate(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
