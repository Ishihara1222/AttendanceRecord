package com.robop.attendancerecord;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] tabTitles;

    public CustomFragmentPagerAdapter(FragmentManager fragmentManager, String[] tabNames) {
        super(fragmentManager);
        this.tabTitles = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    //Fragmentの追加メソッド
    public List<Fragment> addFragment(Fragment ft){
        mFragments.add(ft);
        return null;
    }
}
