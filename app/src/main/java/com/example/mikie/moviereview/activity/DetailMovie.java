package com.example.mikie.moviereview.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.util.ArraySet;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.mikie.moviereview.MainActivity;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.DetailMoviePagerAdapter;
import com.example.mikie.moviereview.model.Genre;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentBackdropPoster;
import com.example.mikie.moviereview.presenter.DetailMoviePresenter;
import com.example.mikie.moviereview.presenter.PosterPresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import java.util.HashMap;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovie extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener, DetailMoviePresenter, PosterPresenter {
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
    @BindView(R.id.type1)
    TextView type1;
    @BindView(R.id.type2)
    TextView type2;
    @BindView(R.id.type3)
    TextView type3;
    @BindView(R.id.judul_movie)
    TextView judul;
    @BindView(R.id.genre)
    TextView genre;
    @BindView(R.id.logo)
    ImageView logo;
    private ProgressDialog dialog;

    private MovieService service;
    private MovieService service2;
    private String TAG = getClass().getSimpleName();
    private Set<String> slideSet = new ArraySet<>();
    private final String IMG = "https://image.tmdb.org/t/p/w500";
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        dialog = new ProgressDialog(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //ambil data dari activity sebelumnya
        Bundle bundle = getIntent().getExtras();
        Integer id = bundle.getInt("id");

        //panggil class movie service
        service = new MovieServiceImpl((DetailMoviePresenter) this, getApplicationContext());
        service.detailMovie(id + "");
        service2 = new MovieServiceImpl((PosterPresenter) this, getApplicationContext());
        service2.posterMovie(id + "", "en", "en");

        //menu collapsing toolbar layoout
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setTitle(id + "");

        tabLayout.addTab(tabLayout.newTab().setText("INFO"));
        tabLayout.addTab(tabLayout.newTab().setText("CAST"));
        tabLayout.addTab(tabLayout.newTab().setText("REVIEWS"));
        Bundle bundle1 = new Bundle();
        bundle1.putString("data", id + "");

        //adapter fragment untuk tab
        DetailMoviePagerAdapter adapter = new DetailMoviePagerAdapter(tabLayout.getTabCount(), getSupportFragmentManager(), bundle1);
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
                break;
            case R.id.m_home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.m_share:
                Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
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

    @Override
    public void loadMovie(Movie movie) {
        String genre_ = "";
        for (Genre genrex : movie.getGenres()) {
            genre_ += genrex.getName() + ",";
        }
        genre_ = genre_.substring(0, genre_.length() - 1);
        int time = movie.getRuntime();
        int hours = time / 60;
        int minutes = time % 60;
        //tampilkan ke layout
        type1.setText(movie.getOriginalLanguage().toUpperCase());
        type2.setText(movie.getReleaseDate().substring(0, 4));
        type3.setText(hours + " hr " + minutes + " mins ");
        genre.setText(genre_);
        judul.setText(movie.getOriginalTitle());
        getSupportActionBar().setTitle(movie.getOriginalTitle());
        Glide.with(this).load(IMG + movie.getPosterPath()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(logo);

        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TestingXX.class));
            }
        });
    }

    @Override
    public void startLoading() {
        dialog.show();
    }

    @Override
    public void stopLoading() {
        dialog.dismiss();
    }

    @Override
    public void loadPoster(ParentBackdropPoster backdropPoster) {
        for (int i = 0; i < backdropPoster.getPosters().size(); i++) {
            if (i == 6) {
                break;
            } else {
                DefaultSliderView textSliderView = new DefaultSliderView(this);
                textSliderView
                        .image(IMG + backdropPoster.getPosters().get(i).getFilePath())
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(this);
                sliderLayout.addSlider(textSliderView);
            }
        }
    }
}
