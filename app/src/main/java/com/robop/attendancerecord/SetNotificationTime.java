package com.robop.attendancerecord;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

class SetNotificationTime {

    private SetNotificationTime(){
        throw new AssertionError(); //間違ってインスタンスを生成した場合、コンストラクタで例外が返される
    }

    static PendingIntent getPendingIntent(Context context, int classNumCode, String intentTypeStr){
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("ClassNumCode", classNumCode);
        intent.setType(intentTypeStr);

        return PendingIntent.getBroadcast(context, classNumCode, intent, 0);
    }

    static Calendar getCalendar(int h, int min){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }

}
