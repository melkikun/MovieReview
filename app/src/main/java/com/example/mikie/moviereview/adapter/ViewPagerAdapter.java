package com.example.mikie.moviereview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.FragmentTab;

/**
 * Created by IT01 on 8/29/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabs;
    private FragmentTab fragmentTab;
    public ViewPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentTab = new FragmentTab();
                fragmentTab.setParameter(0);
                return new FragmentTab();
            case 1:
                fragmentTab = new FragmentTab();
                fragmentTab.setParameter(1);
                return new FragmentTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabs;
    }
}
