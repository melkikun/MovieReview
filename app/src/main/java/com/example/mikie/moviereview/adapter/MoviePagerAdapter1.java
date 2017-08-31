package com.example.mikie.moviereview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.movie.MBoxOffice;
import com.example.mikie.moviereview.fragment.movie.MNowPlaying;
import com.example.mikie.moviereview.fragment.movie.MPopular;
import com.example.mikie.moviereview.fragment.movie.MUpcoming;

/**
 * Created by IT01 on 8/29/2017.
 */

public class MoviePagerAdapter1 extends FragmentStatePagerAdapter {
    int tabs;
    public MoviePagerAdapter1(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }
    private String[] tabTitles = new String[]{"NOW PLAYING", "TOP BOX OFFICE", "UPCOMING", "POPULAR"};

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MNowPlaying();
            case 1:
                return new MBoxOffice();
            case 2:
                return new MUpcoming();
            case 3:
                return new MPopular();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabs;
    }
}
