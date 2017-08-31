package com.example.mikie.moviereview.fragment.movie;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.MovieAdapter1;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.GenreDetail;
import com.example.mikie.moviereview.model.ParentGenreDetail;
import com.example.mikie.moviereview.presenter.GenreDetailPresenter;
import com.example.mikie.moviereview.services.GenreService;
import com.example.mikie.moviereview.services.impl.GenreServicesImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 8/29/2017.
 */

public class MNowPlaying extends Fragment implements GenreDetailPresenter{
    @BindView(R.id.rv_movie)
    RecyclerView recyclerView;
    private Context context;
    private List<GenreDetail> detailList = new ArrayList<>();
    private MovieAdapter1 adapter;
    private ApiMovie apiMovie = ApiRest.retrofit().create(ApiMovie.class);
    private ProgressDialog dialog;
    private int page = 232;
    private String TAG = "Movie Now Playing - ";
    private GenreService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_now_playing, container, false);
        ButterKnife.bind(this, view);
        this.context = getContext();
        service = new GenreServicesImpl(this,this.context);
        adapter = new MovieAdapter1(context, detailList);
        adapter.setLoadMoreListener(new MovieAdapter1.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, page+"");
                        service.firstPage("37", page);
                        page++;
                    }
                });
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(adapter);

        service.firstPage("37", 1);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void print(ParentGenreDetail parentGenreDetail) {
        detailList.addAll(parentGenreDetail.getResults());
        adapter.notifyDataChanged();
    }

    @Override
    public void print2(ParentGenreDetail parentGenreDetail) {
        detailList.add(new GenreDetail(true));
        adapter.notifyItemInserted(detailList.size());
        detailList.remove(detailList.size()-1);
        List<GenreDetail> list2 = parentGenreDetail.getResults();
        if(detailList.size() > 0){
            detailList.addAll(list2);
        }else{
            adapter.setMoreDataAvailable(false);
            Toast.makeText(getContext().getApplicationContext(), "No More Data Available", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataChanged();
    }
}
