package com.ihfazh.simplenotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID = 10;

    public static final String CHANNEL_ID = "test";
    public static final String CHANNEL_NAME = "test_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendNotification(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ihfazh.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setContentTitle("Hello world")
                .setContentText("Haiiiiiiiiiiiiiiiiiiiiiiiiii")
                .setSubText("Ini subtextttttttttttttttttttttttttttttt")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_NAME, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_NAME.toString());
            builder.setChannelId(CHANNEL_NAME);

            if (manager != null){
                manager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (manager != null){
            manager.notify(NOTIFICATION_ID, notification);
        }
    }
}