package com.example.mikie.moviereview.fragment.movie;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.MovieAdapter;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentMovie;
import com.example.mikie.moviereview.presenter.MoviePresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 8/31/2017.
 */

public class MBoxOffice extends Fragment implements MoviePresenter{
    @BindView(R.id.text_box_office)
    TextView text_box_office;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    private MovieService service;
    private String TAG = MBoxOffice.class.getSimpleName();
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_box_office, container, false);
        ButterKnife.bind(this, view);
        text_box_office.setText("xxxx");
        service = new MovieServiceImpl(this, getContext());
        adapter = new MovieAdapter(movieList, getContext());
        adapter.setOnLoadMoreListener(new MovieAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(adapter);
        service.loadMovie("now_playing", null, 1, null );
        return view;
    }

    @Override
    public void first(ParentMovie movie) {
        if(movie.getResults().size() != 0){
            movieList.addAll(movie.getResults());
            adapter.notifyDataChanged();
        }

    }

    @Override
    public void next(ParentMovie movie) {

    }
}
