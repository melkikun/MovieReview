package com.example.mikie.moviereview.services.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.ListGenre;
import com.example.mikie.moviereview.model.ParentGenreDetail;
import com.example.mikie.moviereview.presenter.GenreDetailPresenter;
import com.example.mikie.moviereview.presenter.GenrePresenter;
import com.example.mikie.moviereview.services.GenreService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mikie on 8/24/2017.
 */

public class GenreServicesImpl implements GenreService {
    private GenrePresenter presenter;
    private GenreDetailPresenter presenter2;
    private Context context;
    private ApiMovie apiMovie = ApiRest.retrofit().create(ApiMovie.class);

    public GenreServicesImpl(GenreDetailPresenter presenter2, Context context) {
        this.presenter2 = presenter2;
        this.context = context;
    }

    public GenreServicesImpl(GenrePresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void movie() {
        Observable<ListGenre> observable = apiMovie.test(context.getString(R.string.api_key), "id-ID");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListGenre>() {
                    @Override
                    public void onCompleted() {
                        Log.d("api key", context.getString(R.string.api_key));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ListGenre listGenre) {
                        presenter.success(listGenre);
                    }
                });
    }

    @Override
    public void detailGenre(String id) {
        Observable<ParentGenreDetail> observable = apiMovie.detailGenreMovie(id,this.context.getString(R.string.api_key), null, null, null, null);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentGenreDetail>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(context.getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentGenreDetail parentGenreDetail) {
                        Toast.makeText(context.getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                        presenter2.print(parentGenreDetail);
                    }
                });
    }
}
