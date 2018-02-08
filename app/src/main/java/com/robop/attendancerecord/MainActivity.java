package com.robop.attendancerecord;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private int ALARM_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attendanceNotification();
    }

    private void setAlarmTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());   //現在時刻取得

        calendar.add(Calendar.SECOND, 5);
        /*
        Intent intent = new Intent(getApplicationContext(), AlarmNotification.class);
        intent.putExtra("RequestCode", ALARM_REQUEST_CODE);
        */
    }

    private void attendanceNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("出席しましたか？");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager)getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
