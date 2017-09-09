package com.example.mikie.moviereview.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.detailmovie.CastingMovie;
import com.example.mikie.moviereview.fragment.detailmovie.InfoMovie;
import com.example.mikie.moviereview.fragment.detailmovie.ReviewMovie;

/**
 * Created by IT01 on 9/4/2017.
 */

public class DetailMoviePagerAdapter extends FragmentStatePagerAdapter{
    int tabs;
    private Bundle bundle;
    private String [] title = new String[]{"INFO", "CAST", "REVIEW"};
    public DetailMoviePagerAdapter(int tabs, FragmentManager fm, Bundle bundle) {
        super(fm);
        this.tabs = tabs;
        this.bundle = bundle;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new InfoMovie();
                fragment.setArguments(this.bundle);
                break;
            case 1:
                fragment = new CastingMovie();
                fragment.setArguments(this.bundle);
                break;
            case 2:
                fragment = new ReviewMovie();
                fragment.setArguments(this.bundle);
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return this.tabs;
    }
}
