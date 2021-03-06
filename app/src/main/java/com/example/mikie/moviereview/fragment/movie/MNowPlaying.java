package com.example.mikie.moviereview.fragment.movie;

import android.app.ProgressDialog;
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
 * Created by IT01 on 8/29/2017.
 */

public class MNowPlaying extends Fragment implements ListMoviePresenter {
    @BindView(R.id.rv_movie)
    RecyclerView recyclerView;
    private List<GenreMovie> genreMovieList = new ArrayList<>();
    private MovieAdapter adapter;
    private int page = 2;
    private String TAG = this.getClass().getSimpleName();
    private MovieService service;
    private ProgressDialog dialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_now_playing, container, false);
        ButterKnife.bind(this, view);

        adapter = new MovieAdapter(genreMovieList, getContext());
        adapter.setOnLoadMoreListener(new MovieAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        service.loadMovie("now_playing", null, page, null);
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
        service = new MovieServiceImpl(this, getContext());
        service.loadMovie("now_playing", null, 1, null);
//        dialog = new ProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void first(ParentMovie movie) {
        if (movie.getResults().size() != 0) {
            genreMovieList.addAll(movie.getResults());
            adapter.notifyDataChanged();
        }
    }

    @Override
    public void next(ParentMovie movie) {
        //add loading progress bar
        genreMovieList.add(new GenreMovie(""));
        adapter.notifyItemInserted(genreMovieList.size() - 1);
        //remove loading bar
        genreMovieList.remove(genreMovieList.size() - 1);

        //tampung hasil ke dua
        List<GenreMovie> genreMovieList1 = movie.getResults();
        if (genreMovieList1.size() != 0) {
            this.genreMovieList.addAll(genreMovieList1);
        } else {
            adapter.setMoreDataAvailable(false);
            Toast.makeText(getContext(), "Tidak ada data lagi", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataChanged();
    }

    @Override
    public void startLoading() {
//        dialog.show();

    }

    @Override
    public void stopLoading() {
//        dialog.dismiss();
    }
}
