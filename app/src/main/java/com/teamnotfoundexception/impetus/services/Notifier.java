package com.teamnotfoundexception.impetus.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.teamnotfoundexception.impetus.Databases.EventItem;
import com.teamnotfoundexception.impetus.R;


public class Notifier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        EventItem eventItem = (EventItem) intent.getSerializableExtra("dope");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_whatshot_white_24dp)
                .setContentText(eventItem.getName())
                .setContentText("Event is about to start in 5 minutes");
        NotificationManager mNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification.notify(2,mBuilder.build());

    }
}