package com.example.mikie.moviereview.presenter;

import android.support.v7.widget.RecyclerView;

import com.example.mikie.moviereview.model.ParentPerson;

/**
 * Created by IT01 on 9/14/2017.
 */

public interface MoreFromPersonPresenter {
    void loadMoreFromPerson(ParentPerson parentPerson, RecyclerView.ViewHolder holder);
}
