package com.example.mikie.moviereview.services.impl;

import android.content.Context;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentMovie;
import com.example.mikie.moviereview.presenter.MoviePresenter;
import com.example.mikie.moviereview.services.MovieService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by IT01 on 8/31/2017.
 */

public class MovieServiceImpl implements MovieService{
    private ApiMovie api = ApiRest.retrofit().create(ApiMovie.class);
    private MoviePresenter presenter;
    private Context context;

    public MovieServiceImpl(MoviePresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void loadMovie(String jenis_, String language_, final int page_, String region_) {
        Observable<ParentMovie> observable = api.movie(jenis_, context.getString(R.string.api_key), language_, page_, region_);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentMovie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ParentMovie parentMovie) {
                        if(page_ == 1){
                            presenter.first(parentMovie);
                        }else{
                            presenter.next(parentMovie);
                        }
                    }
                });

    }
}
