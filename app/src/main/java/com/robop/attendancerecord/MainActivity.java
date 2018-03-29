package com.robop.attendancerecord;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        String[] tabNames = getResources().getStringArray(R.array.tabNames);    //TabLayoutに表示する文字を管理する配列

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.pager);

        //曜日の数だけFragment生成
        CustomFragmentPagerAdapter customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabNames);

        viewPager.setAdapter(customFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        SetNotificationTime setNotificationTime = new SetNotificationTime(getApplicationContext());

        //TODO アプリを開くたび初期値設定されてしまうので値保持処理が必要
        Calendar[] calendars = new Calendar[5];
        for (int i=0; i<calendars.length; i++){
            calendars[i] = Calendar.getInstance();
        }
        calendars[0] = setNotificationTime.getCalendar(10, 50);
        calendars[1] = setNotificationTime.getCalendar(12, 40);
        calendars[2] = setNotificationTime.getCalendar(15, 10);
        calendars[3] = setNotificationTime.getCalendar(17, 0);
        calendars[4] = setNotificationTime.getCalendar(18, 50);

        //setNotificationTime.setNotification(calendars);     //通知設定処理
    }

    @Override
    public void onRestart(){
        super.onRestart();

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
                Intent intent = new Intent(this, SettingClassTimeActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

    }


}
