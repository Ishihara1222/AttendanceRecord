package com.robop.attendancerecord;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.TimeZone;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity  {

    Toolbar toolbar;

    ViewPager viewPager;
    TabLayout tabLayout;

    CustomFragmentPagerAdapter customFragmentPagerAdapter;

    int[] endTimeHourGroup;     //通知時間の時間部分をまとめた配列
    int[] endTimeMinuteGroup;   //通知時間の分部分をまとめた配列

    final int INTENT_REQUEST_CODE = 1;  //通知設定(EndTimeActivity)へStartActivityForResultする際のCODE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        String[] tabNames = getResources().getStringArray(R.array.tabNames);    //TabLayoutに表示する文字を管理する配列

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);

        toolbar.setTitle("曜日");
        setSupportActionBar(toolbar);

        initEndTimeArray();     //通知時間管理配列の初期化

        //曜日の数だけFragment生成
        customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabNames);

        viewPager.setAdapter(customFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setNotificationTime();     //通知設定処理
    }

    private void initEndTimeArray(){
        endTimeHourGroup = new int[5];
        endTimeHourGroup[0] = 10;
        endTimeHourGroup[1] = 12;
        endTimeHourGroup[2] = 15;
        endTimeHourGroup[3] = 17;
        endTimeHourGroup[4] = 18;

        endTimeMinuteGroup = new int[5];
        endTimeMinuteGroup[0] = 50;
        endTimeMinuteGroup[1] = 40;
        endTimeMinuteGroup[2] = 10;
        endTimeMinuteGroup[3] = 00;
        endTimeMinuteGroup[4] = 50;
    }

    @Override
    public void onRestart(){
        super.onRestart();

        setNotificationTime();
        //reloadFragmentData();     //Fragment内のListデータ更新
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.alertSetting:     //通知設定画面へ
                Intent intent = new Intent(this, EndTimeActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_CODE);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == INTENT_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                //通知設定時間の数字配列を取得
                endTimeHourGroup = data.getIntArrayExtra("EndTimeHour");
                endTimeMinuteGroup = data.getIntArrayExtra("EndTimeMinute");
            }
        }
    }

    public void setNotificationTime(){
        //TODO 授業が無ければ通知は鳴らさない

        int classExistFlag;
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");

        //現在時刻取得
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeZone(timeZone);

        //TODO 通知時間の数字の変数化
        //通知が鳴る時間の設定
        PendingIntent pendingIntent0 = SetNotificationTime.getPendingIntent(getApplicationContext(),1, "intent0");
        Calendar calendar0 = SetNotificationTime.getCalendar(10, 50);

        PendingIntent pendingIntent1 = SetNotificationTime.getPendingIntent(getApplicationContext(),2, "intent1");
        Calendar calendar1 = SetNotificationTime.getCalendar(12, 40);

        PendingIntent pendingIntent2 = SetNotificationTime.getPendingIntent(getApplicationContext(),3, "intent2");
        Calendar calendar2 = SetNotificationTime.getCalendar(15, 10);

        PendingIntent pendingIntent3 = SetNotificationTime.getPendingIntent(getApplicationContext(),4, "intent3");
        Calendar calendar3 = SetNotificationTime.getCalendar(17, 14);

        PendingIntent pendingIntent4 = SetNotificationTime.getPendingIntent(getApplicationContext(),5, "intent4");
        Calendar calendar4 = SetNotificationTime.getCalendar(17, 16);

        //アラーム設定
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar0.getTimeInMillis(), pendingIntent0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent1);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent2);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), pendingIntent3);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), pendingIntent4);

    }
}
