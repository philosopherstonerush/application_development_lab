package com.bsn.ex_no_11;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.bsn.ex_no_11.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity { ;


    public static MainActivity inst;

    public static MainActivity instance() {
        return inst;
    }

    public void onStart() {
        super.onStart();
        inst=this;
    }

    NotificationManager nm;
    Notification n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TimePicker tp = (TimePicker) findViewById(R.id.timepicker1);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datepicker1);
        final Button b = (Button) findViewById(R.id.button1);

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Calendar now = Calendar.getInstance();
        datePicker.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), null);
        tp.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        tp.setCurrentMinute(now.get(Calendar.MINUTE));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar current = Calendar.getInstance();
                Calendar alarm = Calendar.getInstance();

                alarm.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), tp.getHour(), tp.getCurrentMinute(), 00);
                if(alarm.compareTo(current) <= 0) {
                    Toast.makeText(peekAvailableContext().getApplicationContext(), "invalid", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(MainActivity.instance().peekAvailableContext(), Alarm.class);

                    PendingIntent pi = PendingIntent.getBroadcast(MainActivity.instance().peekAvailableContext(), 123, i, 0);
                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, alarm.getTimeInMillis(), pi);


                }
            }
        });

    }

    public void update_notifications() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.instance().peekAvailableContext()).create();
        alertDialog.setTitle("WAKE UP");
        alertDialog.setMessage("ALARM");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

}