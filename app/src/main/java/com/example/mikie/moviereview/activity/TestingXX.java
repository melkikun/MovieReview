package com.example.mikie.moviereview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.ParentBackdropPoster;
import com.example.mikie.moviereview.presenter.PosterPresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

public class TestingXX extends AppCompatActivity implements PosterPresenter{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_xx);
        MovieService movieService = new MovieServiceImpl(this, getApplicationContext());
        movieService.posterMovie("315635", "en", "en");
    }

    @Override
    public void loadPoster(ParentBackdropPoster backdropPoster) {
        Toast.makeText(getApplicationContext(), "xxxx", Toast.LENGTH_SHORT).show();
        Log.d("xxxx", "xxxx");

    }
}
