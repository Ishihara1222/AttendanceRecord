package com.robop.attendancerecord;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    ViewPager viewPager;
    TabLayout tabLayout;

    CustomFragmentPagerAdapter customFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] tabNames = getResources().getStringArray(R.array.tabNames);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.pager);

        toolbar.setTitle("曜日");
        setSupportActionBar(toolbar);

        customFragmentAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabNames);
        for(int i=0; i<6; i++){
            customFragmentAdapter.addFragment(ScheduleFragment.newInstance());
        }

        viewPager.setAdapter(customFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setAlarmTime();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.alertSetting:
                Intent intent = new Intent(this, EndTimeActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void setAlarmTime(){

        int classExistFlag; //授業が無ければアラームは鳴らさない
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");

        //現在時刻取得
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeZone(timeZone);

        //TODO 1限から5限の終了時刻までループさせて適切なタイマーを設定する

        int[] hourNumGroup = {10, 12, 15, 17, 23};
        int[] minuteNumGroup = {50, 40, 10, 00, 13};

        for (int i=0; i<5; i++){

            Calendar calendarTarget = Calendar.getInstance();
            //通知が鳴る時間の設定
            calendarTarget.setTimeInMillis(System.currentTimeMillis());
            calendarTarget.set(Calendar.HOUR_OF_DAY, hourNumGroup[i]);
            calendarTarget.set(Calendar.MINUTE, minuteNumGroup[i]);
            calendarTarget.set(Calendar.SECOND, 0);

            //ミリ秒取得
            long targetMs = calendarTarget.getTimeInMillis();
            long nowMs = calendarNow.getTimeInMillis();

            //現時刻とターゲット時刻と比較して、ターゲット時刻が未来ならアラーム設定
            if(targetMs >= nowMs){
                Toast.makeText(this, calendarTarget.getTime().toString() + "にアラームをセットしました", Toast.LENGTH_LONG).show();
                Log.i("alarm", calendarTarget.getTime().toString());

                Intent intent = new Intent(getApplicationContext(), AlarmNotification.class);
                int alarmRequestCode = 1;
                intent.putExtra("AlarmRequestCode", alarmRequestCode);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarmRequestCode, intent, 0);

                AlarmManager alarmmanager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmmanager.set(AlarmManager.RTC_WAKEUP, calendarTarget.getTimeInMillis(), pendingIntent);
            }
        }
    }
}
