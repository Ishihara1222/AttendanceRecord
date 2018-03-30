package com.robop.attendancerecord;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class SetNotificationTime {

    private Context context;

    SetNotificationTime(Context context){
        this.context = context;
    }

    private PendingIntent getPendingIntent(int classNumCode, String intentTypeStr){
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("ClassNumCode", classNumCode + 1);
        intent.setType(intentTypeStr);

        return PendingIntent.getBroadcast(context, classNumCode, intent, 0);
    }

    Long getAlarmTime(int h, int min){

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");

        //現在時刻取得
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeZone(timeZone);
        //Log.d("nowTime", String.valueOf(calendarNow.get(Calendar.MONTH) + 1) + "月" + String.valueOf(calendarNow.get(Calendar.DAY_OF_MONTH)) + "日" + String.valueOf(calendarNow.get(Calendar.HOUR_OF_DAY)) + "時" + String.valueOf(calendarNow.get(Calendar.MINUTE)) + "分");

        Calendar calendarAlarm = Calendar.getInstance();
        calendarAlarm.set(Calendar.MONTH, calendarNow.get(Calendar.MONTH));
        calendarAlarm.set(Calendar.DAY_OF_MONTH, calendarNow.get(Calendar.DAY_OF_MONTH));
        calendarAlarm.set(Calendar.HOUR_OF_DAY, h);
        calendarAlarm.set(Calendar.MINUTE, min);
        calendarAlarm.set(Calendar.SECOND, 0);

        //現在時刻を過ぎているかどうか確認
        if (calendarAlarm.compareTo(calendarNow) < 0){
            Date date = new Date(calendarAlarm.getTimeInMillis() + 24 * 60 * 60 * 1000);
            DateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH時mm分");
            Log.d("setAlarmTime", dateFormat.format(date));
            return calendarAlarm.getTimeInMillis() + 24 * 60 * 60 *1000;
        }else{
            Log.d("setAlarmTime", String.valueOf(calendarAlarm.get(Calendar.MONTH) + 1) + "月" + String.valueOf(calendarAlarm.get(Calendar.DAY_OF_MONTH)) + "日" + String.valueOf(calendarAlarm.get(Calendar.HOUR_OF_DAY)) + "時" + String.valueOf(calendarAlarm.get(Calendar.MINUTE)) + "分");
            return calendarAlarm.getTimeInMillis();
        }

    }

    void setNotification(Long[] alarmTimes){
        //TODO 授業が無ければ通知は鳴らさない

        int classExistFlag;

        //通知が鳴る時間の設定
        PendingIntent[] pendingIntents = new PendingIntent[5];
        for (int i=0; i<pendingIntents.length; i++){
            pendingIntents[i] = getPendingIntent(i, "intent" + i);
        }

        //アラーム設定
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null){

            for (int i=0; i<alarmTimes.length; i++){
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimes[i], pendingIntents[i]);
            }

        }

    }

}
