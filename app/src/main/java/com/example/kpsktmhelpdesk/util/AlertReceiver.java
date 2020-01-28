package com.example.kpsktmhelpdesk.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.kpsktmhelpdesk.activity.MainActivity;
import com.example.kpsktmhelpdesk.R;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            createNotification(context, "KPSTKM HelpDesk", "You have new report", "Read Now");
    }

    public void createNotification(Context cxt, String msg, String msgTxt, String msgAlert){
        PendingIntent pendingIntent =PendingIntent.getActivity(cxt,1, new Intent(cxt, MainActivity.class), 0);

        //BuildNotification
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(cxt).setSmallIcon(R.drawable.kpsktm_logo)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgTxt);

        //Defines the Intent to fire when the notification is clicked
        mBuilder.setContentIntent(pendingIntent);

        // Set the default notification option
        // DEFAULT_SOUND : Make sound
        // DEFAULT_VIBRATE : Vibrate
        // DEFAULT_LIGHTS : Use the default light notification
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        //Auto cancels the notification when clicked on the task bar
        mBuilder.setAutoCancel(false);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager notificationManager = (NotificationManager) cxt.getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification
        notificationManager.notify(1,mBuilder.build());


    };
}
