package com.example.mikie.moviereview.services.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.ParentGenreDetail;
import com.example.mikie.moviereview.presenter.GenreDetailPresenter;
import com.example.mikie.moviereview.services.GenreService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mikie on 8/24/2017.
 */

public class GenreServicesImpl implements GenreService {
    private GenreDetailPresenter presenter2;
    private Context context;
    private ApiMovie apiMovie = ApiRest.retrofit().create(ApiMovie.class);

    public GenreServicesImpl(GenreDetailPresenter presenter2, Context context) {
        this.presenter2 = presenter2;
        this.context = context;
    }

    @Override
    public void firstPage(String id, final int page) {
        Observable<ParentGenreDetail> observable = apiMovie.detailGenreMovie(id,this.context.getString(R.string.api_key), null, null, null, page+"");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentGenreDetail>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentGenreDetail parentGenreDetail) {
                        if(page == 1) {
                            presenter2.print(parentGenreDetail);
                        }else{
                            presenter2.print2(parentGenreDetail);
                        }
                    }
                });
    }
}
