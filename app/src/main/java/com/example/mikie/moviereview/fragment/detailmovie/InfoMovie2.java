package com.example.mikie.moviereview.fragment.detailmovie;

import android.nfc.Tag;
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
import android.widget.TextView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.SimilarAdapter;
import com.example.mikie.moviereview.adapter.TrailerAdapter;
import com.example.mikie.moviereview.adapter.VerticalRv;
import com.example.mikie.moviereview.custom.DividerItemDecoration;
import com.example.mikie.moviereview.model.CategoryInfo;
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

public class InfoMovie2 extends Fragment implements DetailMoviePresenter{
    private MovieService service;
    private final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    private final String TAG = getClass().getName();
    @BindView(R.id.rv_vertical)
    RecyclerView recyclerView;
    @BindView(R.id.namacasting)
    TextView namacasting;
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
        CategoryInfo categoryInfo = new CategoryInfo("Ratings");
        CategoryInfo categoryInfo2 = new CategoryInfo("Review");
        CategoryInfo categoryInfo3 = new CategoryInfo("Trailers");
        CategoryInfo categoryInfo4 = new CategoryInfo("More From Author");
        CategoryInfo categoryInfo5 = new CategoryInfo("Similar Movie");
        List<CategoryInfo> list = new ArrayList<>();
        list.add(categoryInfo);
        list.add(categoryInfo2);
        list.add(categoryInfo3);
        list.add(categoryInfo4);
        list.add(categoryInfo5);
        VerticalRv adapter = new VerticalRv(list, getContext(), movie);
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
