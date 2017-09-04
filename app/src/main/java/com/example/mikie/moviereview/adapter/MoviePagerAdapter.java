package com.example.mikie.moviereview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.movie.MNowPlaying;
import com.example.mikie.moviereview.fragment.movie.MPopular;
import com.example.mikie.moviereview.fragment.movie.MTopRated;
import com.example.mikie.moviereview.fragment.movie.MUpcoming;

/**
 * Created by IT01 on 8/29/2017.
 */

public class MoviePagerAdapter extends FragmentStatePagerAdapter {
    int tabs;
    public MoviePagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }
    private String[] tabTitles = new String[]{"NOW PLAYING", "POPULAR", "TOP RATED", "UPCOMMING"};

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
                return new MPopular();
            case 2:
                return new MTopRated();
            case 3:
                return new MUpcoming();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabs;
    }
}
