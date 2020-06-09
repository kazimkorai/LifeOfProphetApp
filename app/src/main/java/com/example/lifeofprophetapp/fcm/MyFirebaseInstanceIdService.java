package com.example.lifeofprophetapp.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;



import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseInstanceIdService {
        //extends FirebaseMessagingService {
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Map<String, String> params = remoteMessage.getData();
//        JSONObject object = new JSONObject(params);
//        final String NOTIFICATION_CHANNEL_ID = getString(R.string.app_name);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        final long[] pattern = {0, 1000, 500, 1000};
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    NOTIFICATION_CHANNEL_ID, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
//            channel.setDescription("");
//            channel.enableLights(true);
//            channel.setVibrationPattern(pattern);
//            channel.enableVibration(true);
//            notificationManager.createNotificationChannel(channel);
//        }
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
//                NOTIFICATION_CHANNEL_ID);
//        notificationBuilder.setAutoCancel(true)
//                .setContentTitle(getString(R.string.app_name))
//                .setContentText(remoteMessage.getNotification().getBody())
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.download)
//                .setColor(Color.BLUE);
//        notificationManager.notify(1000, notificationBuilder.build());

}
