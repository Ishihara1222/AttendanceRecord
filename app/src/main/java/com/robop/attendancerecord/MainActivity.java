package com.robop.attendancerecord;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        return true;
    }
}
