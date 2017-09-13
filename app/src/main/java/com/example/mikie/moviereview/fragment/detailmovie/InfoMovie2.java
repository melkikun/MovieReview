package com.example.mikie.moviereview.fragment.detailmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.MoreInfoAdapter;
import com.example.mikie.moviereview.custom.DividerItemDecoration;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.presenter.DetailMoviePresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/4/2017.
 */

public class InfoMovie2 extends Fragment implements DetailMoviePresenter {
    private MovieService service;
    private final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    private final String TAG = getClass().getName();
    @BindView(R.id.rv_vertical)
    RecyclerView recyclerView;
    private List<String> category = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_detail_movie2, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        service = new MovieServiceImpl(this, getContext());
        service.detailMovie(args.getString("data"), null, "videos,credits,images,similar");
        return view;
    }

    @Override
    public void loadMovie(Movie movie) {
        category.add("");
        category.add("");
        category.add("");
        category.add("");
        category.add("");
        category.add("");
        MoreInfoAdapter adapter = new MoreInfoAdapter(category, getContext(), movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
