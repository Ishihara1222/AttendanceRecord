package com.robop.attendancerecord;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int classNumCode = intent.getIntExtra("ClassNumCode", -1);  //授業時限数の受取
        Log.d("ClassNumCode", String.valueOf(classNumCode));

        final int ATTEND = 0;
        final int ABSENT = 1;
        final int LATE = 2;

        String channelId = "default";

        String title = classNumCode + "限の授業に出席しましたか?";

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, classNumCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent actionAttendIntent = new Intent(context, AttendanceResultActivity.class);
        Intent actionAbsentIntent = new Intent(context, AttendanceResultActivity.class);
        Intent actionLateIntent = new Intent(context, AttendanceResultActivity.class);

        actionAttendIntent.putExtra("AttendResult", ATTEND);
        actionAttendIntent.putExtra("ClassNumResult", classNumCode);
        actionAbsentIntent.putExtra("AttendResult", ABSENT);
        actionAbsentIntent.putExtra("ClassNumResult", classNumCode);
        actionLateIntent.putExtra("AttendResult", LATE);
        actionLateIntent.putExtra("ClassNumResult", classNumCode);

        PendingIntent actionAttendPendingIntent = PendingIntent.getActivity(context, 6, actionAttendIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent actionAbsentPendingIntent = PendingIntent.getActivity(context, 7 , actionAbsentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent actionLatePendingIntent = PendingIntent.getActivity(context, 8, actionLateIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Action actionAttend = new Notification.Action(0, "出席", actionAttendPendingIntent);
        Notification.Action actionAbsent = new Notification.Action(0, "欠席", actionAbsentPendingIntent);
        Notification.Action actionLate = new Notification.Action(0, "遅刻", actionLatePendingIntent);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, context.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(title);
            notificationChannel.enableVibration(true);
            notificationChannel.canShowBadge();
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);

            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationChannel.setShowBadge(true);

            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);

                Notification notification = new Notification.Builder(context, channelId)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText(title)
                        .setAutoCancel(true)
                        .addAction(actionAttend)
                        .addAction(actionAbsent)
                        .addAction(actionLate)
                        .setContentIntent(resultPendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .build();

                notificationManager.notify(R.string.app_name, notification);

            }
        }else{

            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(title);
            builder.setAutoCancel(true);
            builder.addAction(actionAttend);
            builder.addAction(actionAbsent);
            builder.addAction(actionLate);
            builder.setContentIntent(resultPendingIntent);
            builder.setWhen(System.currentTimeMillis());

            builder.build().flags |= Notification.FLAG_AUTO_CANCEL;

            assert notificationManager != null;
            notificationManager.notify(1, builder.build());
        }

    }

}
