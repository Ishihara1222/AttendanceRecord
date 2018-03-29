package com.robop.attendancerecord;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles; //月〜土

    CustomFragmentPagerAdapter(FragmentManager fragmentManager, String[] tabNames) {
        super(fragmentManager);
        this.tabTitles = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position); //Fragment生成メソッドの呼び出し
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
