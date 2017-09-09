package com.example.mikie.moviereview.presenter;

import com.example.mikie.moviereview.model.ParentMovie;

/**
 * Created by IT01 on 8/31/2017.
 */

public interface ListMoviePresenter {
    void first(ParentMovie movie);
    void next(ParentMovie movie);
    void startLoading();
    void stopLoading();
}
