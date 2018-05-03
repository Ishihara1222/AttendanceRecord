package com.robop.attendancerecord;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity  {

    String[] tabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        tabNames = getResources().getStringArray(R.array.tabNames);    //TabLayoutに表示する文字を管理する配列

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.pager);

        //曜日の数だけFragment生成
        CustomFragmentPagerAdapter customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabNames);

        viewPager.setAdapter(customFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        SetNotificationTime setNotificationTime = new SetNotificationTime(getApplicationContext());

        //TODO アプリを開くたび初期値設定されてしまうので値保持処理が必要
        Long[] alarmTimes = new Long[5];
        alarmTimes[0] = setNotificationTime.getAlarmTime(10, 50);
        alarmTimes[1] = setNotificationTime.getAlarmTime(12, 40);
        alarmTimes[2] = setNotificationTime.getAlarmTime(15, 10);
        alarmTimes[3] = setNotificationTime.getAlarmTime(17, 0);
        alarmTimes[4] = setNotificationTime.getAlarmTime(18, 50);

        setNotificationTime.setNotification(alarmTimes);     //通知設定処理
    }

    @Override
    public void onResume(){
        super.onResume();

        //TODO Fragment内のListデータ更新
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
                Intent intent = new Intent(this, SettingClassTimeActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
