package com.example.mikie.moviereview.presenter;

import android.support.v7.widget.RecyclerView;

import com.example.mikie.moviereview.model.ParentCollection;

/**
 * Created by IT01 on 9/13/2017.
 */

public interface CollectionMoviePresenter {
    void loadCollection(ParentCollection parentCollection, RecyclerView.ViewHolder holder);
}
