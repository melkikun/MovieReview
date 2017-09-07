package com.example.mikie.moviereview.fragment.detailmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.ReviewAdapter;
import com.example.mikie.moviereview.custom.DividerItemDecoration;
import com.example.mikie.moviereview.model.ParentReview;
import com.example.mikie.moviereview.presenter.ReviewPresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/6/2017.
 */

public class Review extends Fragment implements ReviewPresenter{
    @BindView(R.id.rv_review)
    RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private MovieService service;
    private final String TAG = getClass().getCanonicalName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_detail_movie, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        service = new MovieServiceImpl(this, getContext());
        service.reviewMovie(args.getString("data"), "en", null);
        return view;
    }

    @Override
    public void loadReview(ParentReview parentReview) {
        adapter = new ReviewAdapter(parentReview.getResults(), getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }
}
