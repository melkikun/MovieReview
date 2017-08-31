package com.example.mikie.moviereview.presenter;

import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentMovie;

/**
 * Created by IT01 on 8/31/2017.
 */

public interface MoviePresenter {
    public void first(ParentMovie movie);
    public void next(ParentMovie movie);
}
