package com.robop.attendancerecord;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    int currentPageNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        //TODO AlarmReceiverからのgetIntentを処理
        Intent intent = getIntent();
        if (intent != null){
            getResultAttend(intent);
        }



        String[] tabNames = getResources().getStringArray(R.array.tabNames);    //TabLayoutに表示する文字を管理する配列

        TabLayout tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);

        if (savedInstanceState == null){
            //曜日の数だけFragment生成
            PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabNames);

            viewPager.setAdapter(pagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }

        viewPager.addOnPageChangeListener(this);

    }

    private void getResultAttend(Intent intent){
        final int attendResult = intent.getIntExtra("attendResult", -1);
        final int attendClassNum = intent.getIntExtra("classNumResult", -1);

        Log.d("attendResult", String.valueOf(attendResult));
        Log.d("classNumResult", String.valueOf(attendClassNum));

        //TODO Realmに保存
        /*
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm realm = Realm.getInstance(realmConfiguration);
        */

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_SETTLING){
            currentPageNum = viewPager.getCurrentItem();
        }
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
                Intent intent = new Intent(this, SettingClassTimeActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
