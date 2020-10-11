package com.ihfazh.simplenotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String NOTIF_NAME = "test";
    public static final String NOTIF_CHANNEL = "test_channel";
    public static final int NOTIF_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIF_NAME)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setContentTitle("Hello world")
                .setContentText("Haiiiiiiiiiiiiiiiiiiiiiiiiii")
                .setSubText("Ini subtextttttttttttttttttttttttttttttt")
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL, NOTIF_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(NOTIF_CHANNEL.toString());
            builder.setChannelId(NOTIF_CHANNEL);

            if (manager != null){
                manager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (manager != null){
            manager.notify(NOTIF_ID, notification);
        }
    }
}