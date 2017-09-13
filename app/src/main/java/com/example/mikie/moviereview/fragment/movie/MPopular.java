package com.example.mikie.moviereview.fragment.movie;

import android.content.Intent;
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
import com.example.mikie.moviereview.activity.DetailMovie;
import com.example.mikie.moviereview.adapter.MovieAdapter;
import com.example.mikie.moviereview.custom.RecyclerItemClickListener;
import com.example.mikie.moviereview.model.GenreMovie;
import com.example.mikie.moviereview.model.ParentMovie;
import com.example.mikie.moviereview.presenter.ListMoviePresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/4/2017.
 */

public class MPopular extends Fragment implements ListMoviePresenter {
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    private List<GenreMovie> genreMovieList = new ArrayList<>();
    private MovieAdapter adapter;
    private MovieService service;
    private int page = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_popular, container, false);
        ButterKnife.bind(this, view);
        adapter = new MovieAdapter(genreMovieList, getContext());
        service = new MovieServiceImpl(this, getContext());
        adapter.setOnLoadMoreListener(new MovieAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        service.loadMovie("popular", null, page, null);
                        page++;
                    }
                });
            }
        });
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), DetailMovie.class);
                intent.putExtra("id", genreMovieList.get(position).getId());
                startActivity(intent);
            }
        }));
        service.loadMovie("popular", null, 1, null);
        return view;
    }

    @Override
    public void first(ParentMovie movie) {
        genreMovieList.addAll(movie.getResults());
        adapter.notifyDataChanged();
    }

    @Override
    public void next(ParentMovie movie) {
        genreMovieList.add(new GenreMovie(""));
        adapter.notifyItemInserted(genreMovieList.size());
        genreMovieList.remove(genreMovieList.size()-1);
        List<GenreMovie> genreMovieList1 = movie.getResults();
        if(genreMovieList1.size()!=0){
            genreMovieList.addAll(genreMovieList1);
        }else{
            adapter.setMoreDataAvailable(false);
            Toast.makeText(getContext(), "Tidak ada data lagi", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataChanged();
    }

    @Override
    public void startLoading() {
    }

    @Override
    public void stopLoading() {
    }
}
