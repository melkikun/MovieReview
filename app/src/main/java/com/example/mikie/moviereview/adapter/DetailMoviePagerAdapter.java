package com.example.mikie.moviereview.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mikie.moviereview.fragment.detailmovie.Casting;
import com.example.mikie.moviereview.fragment.detailmovie.Info;
import com.example.mikie.moviereview.fragment.detailmovie.Review;

/**
 * Created by IT01 on 9/4/2017.
 */

public class DetailMoviePagerAdapter extends FragmentStatePagerAdapter{
    int tabs;
    private String [] title = new String[]{"INFO", "CAST", "REVIEW"};
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
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new Info();
                break;
            case 1:
                fragment = new Casting();
                break;
            case 2:
                fragment = new Review();
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
