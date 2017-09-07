package com.example.mikie.moviereview.presenter;

import com.example.mikie.moviereview.model.Movie;

/**
 * Created by IT01 on 9/7/2017.
 */

public interface DetailMoviePresenter {
    void loadMovie(Movie movie);
    void startLoading();
    void stopLoading();
}
