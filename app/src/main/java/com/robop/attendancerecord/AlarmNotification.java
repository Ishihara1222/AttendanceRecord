package com.robop.attendancerecord;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class AlarmNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int requestCode = intent.getIntExtra("AlarmRequestCode", 0);
        final int ATTEND = 0;
        final int ABSENT = 1;
        final int LATE = 2;

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent actionAttendIntent = new Intent(context, AttendanceResultActivity.class);
        Intent actionAbsentIntent = new Intent(context, AttendanceResultActivity.class);
        Intent actionLateIntent = new Intent(context, AttendanceResultActivity.class);

        actionAttendIntent.putExtra("AttendResult", ATTEND);
        actionAbsentIntent.putExtra("AttendResult", ABSENT);
        actionLateIntent.putExtra("AttendResult", LATE);

        PendingIntent actionAttendPendingIntent = PendingIntent.getActivity(context, 0, actionAttendIntent, 0);
        PendingIntent actionAbsentPendingIntent = PendingIntent.getActivity(context, 0 , actionAbsentIntent, 0);
        PendingIntent actionLatePendingIntent = PendingIntent.getActivity(context, 0, actionLateIntent, 0);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("出席しましたか?");
        builder.addAction(0, "出席", actionAttendPendingIntent);
        builder.addAction(0, "欠席", actionAbsentPendingIntent);
        builder.addAction(0, "遅刻", actionLatePendingIntent);
        builder.setContentIntent(resultPendingIntent);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }

}
