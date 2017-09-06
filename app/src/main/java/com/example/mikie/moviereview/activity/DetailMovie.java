package com.example.mikie.moviereview.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.mikie.moviereview.MainActivity;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.DetailMoviePagerAdapter;
import com.example.mikie.moviereview.fragment.detailmovie.Casting;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovie extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager pager;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.slider)
    SliderLayout sliderLayout;
    private String TAG =  getClass().getSimpleName();
    private HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        Integer id = bundle.getInt("id");
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setTitle(id+"");
        hashMap.put("Android CupCake", "http://androidblog.esy.es/images/cupcake-1.png");
        hashMap.put("Android Donut", "http://androidblog.esy.es/images/donut-2.png");
        hashMap.put("Android Eclair", "http://androidblog.esy.es/images/eclair-3.png");
        hashMap.put("Android Froyo", "http://androidblog.esy.es/images/froyo-4.png");
        hashMap.put("Android GingerBread", "http://androidblog.esy.es/images/gingerbread-5.png");

        for (String name : hashMap.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            textSliderView
                    .image(hashMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);
            sliderLayout.addSlider(textSliderView);
        }

        tabLayout.addTab(tabLayout.newTab().setText("INFO"));
        tabLayout.addTab(tabLayout.newTab().setText("CAST"));
        tabLayout.addTab(tabLayout.newTab().setText("REVIEWS"));

        DetailMoviePagerAdapter adapter = new DetailMoviePagerAdapter(tabLayout.getTabCount(), getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.m_home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            case R.id.m_share:
                Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_LONG).show();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater  menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detail_movie, menu);
        return true;
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
