package com.example.mikie.moviereview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.MoviePagerAdapter1;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 8/29/2017.
 */

public class MovieFragment extends Fragment{
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager pager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.movie_layout, container, false);
        ButterKnife.bind(this, view);

        tabLayout.addTab(tabLayout.newTab().setText("NOW PLAYING"));
        tabLayout.addTab(tabLayout.newTab().setText("TOP BOX OFFICE"));
        tabLayout.addTab(tabLayout.newTab().setText("UPCOMING"));
        tabLayout.addTab(tabLayout.newTab().setText("POPULAR"));
        tabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.cardview_light_background),
                ContextCompat.getColor(getContext(), R.color.cardview_light_background)
        );

        MoviePagerAdapter1 adapter = new MoviePagerAdapter1(getFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("page", tab.getPosition()+"");
                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }


}
