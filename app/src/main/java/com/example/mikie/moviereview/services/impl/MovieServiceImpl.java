package com.example.mikie.moviereview.services.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.activity.DetailMovie;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentBackdropPoster;
import com.example.mikie.moviereview.model.ParentCastingCrew;
import com.example.mikie.moviereview.model.ParentMovie;
import com.example.mikie.moviereview.model.Poster;
import com.example.mikie.moviereview.presenter.CastingPresenter;
import com.example.mikie.moviereview.presenter.DetailMoviePresenter;
import com.example.mikie.moviereview.presenter.MoviePresenter;
import com.example.mikie.moviereview.presenter.PosterPresenter;
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
    private Context context;
    private MoviePresenter moviePresenter;
    private CastingPresenter castingPresenter;
    private DetailMoviePresenter detailMoviePresenter;
    private PosterPresenter posterPresenter;
    private ProgressDialog dialog;

    public MovieServiceImpl(MoviePresenter moviePresenter, Context context) {
        this.moviePresenter = moviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(CastingPresenter castingPresenter, Context context) {
        this.castingPresenter = castingPresenter;
        this.context = context;
    }

    public MovieServiceImpl(DetailMoviePresenter detailMoviePresenter, Context context) {
        this.detailMoviePresenter = detailMoviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(PosterPresenter posterPresenter, Context context) {
        this.posterPresenter = posterPresenter;
        this.context = context;
    }



    @Override
    public void loadMovie(String jenis_, String language_, final int page_, String region_) {
        dialog =  new ProgressDialog(context, R.style.MyTheme);
//        dialog.setCancelable(false);
//        dialog.setProgressStyle(this.context.R.);
        dialog.show();
        moviePresenter.startLoading();
        Observable<ParentMovie> observable = api.getMovie(jenis_, context.getString(R.string.api_key), language_, page_, region_);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentMovie>() {
                    @Override
                    public void onCompleted() {
                        moviePresenter.stopLoading();
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentMovie parentMovie) {
                        if(page_ == 1){
                            moviePresenter.first(parentMovie);
                        }else{
                            moviePresenter.next(parentMovie);
                        }
                    }
                });

    }

    @Override
    public void castingMovie(String id) {
        Observable<ParentCastingCrew> observable = api.getCasting(id, context.getString(R.string.api_key));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentCastingCrew>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentCastingCrew parentCastingCrew) {
                        castingPresenter.load(parentCastingCrew);
                    }
                });
    }

    @Override
    public void detailMovie(String id) {
        detailMoviePresenter.startLoading();
        Observable<Movie> observable = api.getDetailMovie(id, this.context.getString(R.string.api_key), null, null);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        detailMoviePresenter.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(Movie movie) {
                        detailMoviePresenter.loadMovie(movie);
                    }
                });
    }

    @Override
    public void posterMovie(String id, String language, String iil) {
        Observable<ParentBackdropPoster> observable = api.getPosterMovie(id, this.context.getString(R.string.api_key),language, iil);
       observable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<ParentBackdropPoster>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.d("error", e.getMessage());
                   }

                   @Override
                   public void onNext(ParentBackdropPoster parentBackdropPoster) {
                       posterPresenter.loadPoster(parentBackdropPoster);
                   }
               });
    }
}
