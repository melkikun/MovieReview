package com.example.mikie.moviereview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.detail.Info;

/**
 * Created by IT01 on 9/4/2017.
 */

public class DetailMoviePagerAdapter extends FragmentStatePagerAdapter{
    int tabs;
    private String [] title = new String[]{"tab 1", "tab 2", "tab 3"};
    public DetailMoviePagerAdapter(int tabs, FragmentManager fm) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Info();
            case 1:
                return new Info();
            case 2:
                return new Info();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabs;
    }
}
