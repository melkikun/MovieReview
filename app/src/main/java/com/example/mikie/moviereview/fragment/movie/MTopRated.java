package com.example.mikie.moviereview.fragment.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
 * Created by IT01 on 8/29/2017.
 */

public class MTopRated extends Fragment implements MoviePresenter {
    @BindView(R.id.rv_movie)
    RecyclerView recyclerView;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter adapter;
    private int page = 2;
    private String TAG = this.getClass().getSimpleName();
    private MovieService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_top_rated, container, false);
        ButterKnife.bind(this, view);
        adapter = new MovieAdapter(movieList, getContext());
        adapter.setOnLoadMoreListener(new MovieAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        service.loadMovie("top_rated", null, page, null);
                        page++;
                    }
                });
            }
        });
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        service = new MovieServiceImpl(this, getContext());
        service.loadMovie("top_rated", null, 1, null);
        return view;
    }

    @Override
    public void first(ParentMovie movie) {
        if (movie.getResults().size() != 0) {
            movieList.addAll(movie.getResults());
            adapter.notifyDataChanged();
        }
    }

    @Override
    public void next(ParentMovie movie) {
        //add loading progress bar
        movieList.add(new Movie(""));
        adapter.notifyItemInserted(movieList.size() - 1);
        //remove loading bar
        movieList.remove(movieList.size()-1);

        //tampung hasil ke dua
        List<Movie> movieList1 = movie.getResults();
        if (movieList1.size()!=0){
            this.movieList.addAll(movieList1);
        }else{
            adapter.setMoreDataAvailable(false);
            Toast.makeText(getContext(), "Tidak ada data lagi", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataChanged();
    }
}
