package com.example.mikie.moviereview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.ViewPagerAdapter;

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
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("1"));
        tabLayout.addTab(tabLayout.newTab().setText("2"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
