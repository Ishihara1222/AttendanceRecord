package com.robop.attendancerecord;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class AlarmNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int classNumCode = intent.getIntExtra("ClassNumCode", -1) + 1;

        final int ATTEND = 0;
        final int ABSENT = 1;
        final int LATE = 2;

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, classNumCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent actionAttendIntent = new Intent(context, AttendanceResultActivity.class);
        Intent actionAbsentIntent = new Intent(context, AttendanceResultActivity.class);
        Intent actionLateIntent = new Intent(context, AttendanceResultActivity.class);

        actionAttendIntent.putExtra("AttendResult", ATTEND);
        actionAttendIntent.putExtra("ClassNumCode", classNumCode);
        actionAbsentIntent.putExtra("AttendResult", ABSENT);
        actionAbsentIntent.putExtra("ClassNumCode", classNumCode);
        actionLateIntent.putExtra("AttendResult", LATE);
        actionLateIntent.putExtra("ClassNumCode", classNumCode);

        PendingIntent actionAttendPendingIntent = PendingIntent.getActivity(context, 1, actionAttendIntent, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent actionAbsentPendingIntent = PendingIntent.getActivity(context, 2 , actionAbsentIntent, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent actionLatePendingIntent = PendingIntent.getActivity(context, 3, actionLateIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(classNumCode + "限の授業に出席しましたか?");
        builder.setAutoCancel(true);
        builder.addAction(0, "出席", actionAttendPendingIntent);
        builder.addAction(0, "欠席", actionAbsentPendingIntent);
        builder.addAction(0, "遅刻", actionLatePendingIntent);
        builder.setContentIntent(resultPendingIntent);
        builder.setWhen(System.currentTimeMillis());

        builder.build().flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(1, builder.build());
    }

}
