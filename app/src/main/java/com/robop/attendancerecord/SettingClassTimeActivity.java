package com.robop.attendancerecord;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class SettingClassTimeActivity extends AppCompatActivity implements View.OnClickListener {

    Calendar[] calendars;
    SetNotificationTime setNotificationTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time);

        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        findViewById(R.id.class1Button).setOnClickListener(this);
        findViewById(R.id.class2Button).setOnClickListener(this);
        findViewById(R.id.class3Button).setOnClickListener(this);
        findViewById(R.id.class4Button).setOnClickListener(this);
        findViewById(R.id.class5Button).setOnClickListener(this);
        findViewById(R.id.saveTime).setOnClickListener(this);

        setNotificationTime = new SetNotificationTime(getApplicationContext());

        //TODO 値保持処理
        calendars = new Calendar[5];
        calendars[0] = setNotificationTime.getCalendar(10, 50);
        calendars[1] = setNotificationTime.getCalendar(12, 40);
        calendars[2] = setNotificationTime.getCalendar(15, 10);
        calendars[3] = setNotificationTime.getCalendar(17, 0);
        calendars[4] = setNotificationTime.getCalendar(18, 50);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.class1Button:
                setTimeText(R.id.class1Time);
                break;

            case R.id.class2Button:
                setTimeText(R.id.class2Time);
                break;

            case R.id.class3Button:
                setTimeText(R.id.class3Time);
                break;

            case R.id.class4Button:
                setTimeText(R.id.class4Time);
                break;

            case R.id.class5Button:
                setTimeText(R.id.class5Time);
                break;

            case R.id.saveTime:
                reloadNotificationTime();
                Toast.makeText(this, "通知時間を更新しました", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    //TODO 1限<2限<3限...となるように設定時間の入力制限
    private void setTimeText(final int textId){
        new TimePickerDialog(SettingClassTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                switch (textId){
                    case R.id.class1Time:
                        //class1Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                        //class1Minute = Integer.valueOf(String.format("%02d", minute));
                        calendars[0] = setNotificationTime.getCalendar(hourOfDay, minute);
                        break;

                    case R.id.class2Time:
                        //class2Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                        //class2Minute = Integer.valueOf(String.format("%02d", minute));
                        calendars[1] = setNotificationTime.getCalendar(hourOfDay, minute);
                        break;

                    case R.id.class3Time:
                        //class3Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                        //class3Minute = Integer.valueOf(String.format("%02d", minute));
                        calendars[2] = setNotificationTime.getCalendar(hourOfDay, minute);
                        break;

                    case R.id.class4Time:
                        //class4Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                        //class4Minute = Integer.valueOf(String.format("%02d", minute));
                        calendars[3] = setNotificationTime.getCalendar(hourOfDay, minute);
                        break;

                    case R.id.class5Time:
                        //class5Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                        //class5Minute = Integer.valueOf(String.format("%02d", minute));
                        calendars[4] = setNotificationTime.getCalendar(hourOfDay, minute);
                        break;
                }

                TextView textView = findViewById(textId);
                textView.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 0, 0, true).show();
    }

    //TODO 変更前アラームの削除と再設定
    private void reloadNotificationTime(){

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //アラーム削除
        for (int requestCode = 1; requestCode <= 5; requestCode++){
            Intent intent = new Intent(SettingClassTimeActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingClassTimeActivity.this, requestCode, intent, 0);

            pendingIntent.cancel();
            assert alarmManager != null;
            alarmManager.cancel(pendingIntent);
        }

        setNotificationTime.setNotification(calendars);

    }
}











