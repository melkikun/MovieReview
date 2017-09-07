package com.example.mikie.moviereview.fragment.detailmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.ParentReview;
import com.example.mikie.moviereview.presenter.ReviewPresenter;

import butterknife.BindView;

/**
 * Created by IT01 on 9/6/2017.
 */

public class Review extends Fragment implements ReviewPresenter{
    @BindView(R.id.rv_review)
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_detail_movie, container, false);
        return view;
    }

    @Override
    public void loadReview(ParentReview parentReview) {

    }
}
