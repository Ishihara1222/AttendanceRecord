package com.robop.attendancerecord;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.TimeZone;

class SetNotificationTime {

    private Context context;

    public SetNotificationTime(Context context){
        this.context = context;
    }

    private PendingIntent getPendingIntent(int classNumCode, String intentTypeStr){
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("ClassNumCode", classNumCode);
        intent.setType(intentTypeStr);

        return PendingIntent.getBroadcast(context, classNumCode, intent, 0);
    }

    Calendar getCalendar(int h, int min){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }

    void setNotification(Calendar[] calendars){
        //TODO 授業が無ければ通知は鳴らさない

        int classExistFlag;
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");

        //現在時刻取得
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeZone(timeZone);

        //通知が鳴る時間の設定
        PendingIntent[] pendingIntents = new PendingIntent[5];
        for (int i=0; i<pendingIntents.length; i++){
            pendingIntents[i] = getPendingIntent(i, "intent" + i);
        }

        //アラーム設定
        //TODO 現在時刻を無視して1番目から通知が来る
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendars[0].getTimeInMillis(), pendingIntents[0]);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendars[1].getTimeInMillis(), pendingIntents[1]);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendars[2].getTimeInMillis(), pendingIntents[2]);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendars[3].getTimeInMillis(), pendingIntents[3]);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendars[4].getTimeInMillis(), pendingIntents[4]);

    }

}
