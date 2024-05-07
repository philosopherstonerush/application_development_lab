package com.bsn.ex_no_10;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.app.NotificationManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity implements LocationListener {

    NotificationManager nm;
    Notification n;

    public static MainActivity inst;

    public static MainActivity instance() {
        return inst;
    }

    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("created");
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        System.out.println("notificationManager" + nm.toString());
        n = new Notification(R.drawable.ic_launcher_background, "SMS", System.currentTimeMillis());

        createNotificationChannel();

    }


    public void update_notification(String no, String mgs) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.instance().peekAvailableContext()).create();
        alertDialog.setTitle(no);
        alertDialog.setMessage(mgs);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}