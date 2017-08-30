package com.example.mikie.moviereview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.MovieNowPlaying;

/**
 * Created by IT01 on 8/29/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabs;
    public ViewPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MovieNowPlaying();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabs;
    }
}
