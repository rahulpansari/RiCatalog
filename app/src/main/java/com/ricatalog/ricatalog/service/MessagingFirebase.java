package com.ricatalog.ricatalog.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.SplashActivity;

import java.util.HashMap;
import java.util.Map;

public class MessagingFirebase extends FirebaseMessagingService {
    private final String channel="RI Channel";
    NotificationCompat.Builder builder;
    public static int value=0;
    int not_id=101;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        createnotificationchannel();
        Map<String,String> notificationdata=new HashMap<>();
        notificationdata=remoteMessage.getData();
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder = new NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.drawable.diamondnotification)
                .setContentTitle(notificationdata.get("title"))
                .setContentText(notificationdata.get("message"))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(notificationdata.get("message")).setSummaryText("All New Collections"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.rilogo))
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        Intent notifyIntent = new Intent(this, SplashActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(notifyPendingIntent);
        builder.setAutoCancel(true);
            createnotificationchannel();
            showNotification();
    }

    public void createnotificationchannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name="RI NOTIFICATIONS";
            int imp= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel nc= new NotificationChannel(channel,name,imp);
            nc.enableVibration(true);
            NotificationManager notMan=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .build();
            nc.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),audioAttributes);
            nc.enableVibration(true);
            nc.enableLights(true);
            nc.setVibrationPattern(new long[]{0L,25L,50L,100L});
            notMan.createNotificationChannel(nc);
        }
    }
    public void showNotification()
    {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(147, builder.build());
    }

}
