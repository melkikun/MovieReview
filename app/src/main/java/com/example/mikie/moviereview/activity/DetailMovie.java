package com.example.mikie.moviereview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.DetailMoviePagerAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovie extends AppCompatActivity {
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
//    @BindView(R.id.carouselView)
//    CarouselView carouselView;
    @BindView(R.id.slider)
    SliderLayout sliderLayout;
    private String TAG =  getClass().getSimpleName();
    private int [] img = {R.drawable.alarm, R.drawable.contact, R.drawable.google};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        Bundle bundle = getIntent().getExtras();
        Integer id = bundle.getInt("id");
        TextSliderView textSliderView = new TextSliderView(this);
        for(int i = 0; i < 5; i ++)
        textSliderView
                .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");


        sliderLayout.addSlider(textSliderView);

//        carouselView.setPageCount(img.length);
//        carouselView.setImageListener(new ImageListener() {
//            @Override
//            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(img[position]);
//            }
//        });

        tabLayout.addTab(tabLayout.newTab().setText("TAB 1"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 2"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 3"));

        tabLayout.setTabTextColors(
                ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background),
                ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background)
        );

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
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        sliderLayout.startAutoCycle();
        super.onStop();
    }
}
