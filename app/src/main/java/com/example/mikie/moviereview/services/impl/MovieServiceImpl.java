package com.example.mikie.moviereview.services.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentBackdropPoster;
import com.example.mikie.moviereview.model.ParentCastingCrew;
import com.example.mikie.moviereview.model.ParentCollection;
import com.example.mikie.moviereview.model.ParentMovie;
import com.example.mikie.moviereview.model.ParentReview;
import com.example.mikie.moviereview.model.ParentVideo;
import com.example.mikie.moviereview.presenter.CastingMoviePresenter;
import com.example.mikie.moviereview.presenter.CollectionMoviePresenter;
import com.example.mikie.moviereview.presenter.DetailMoviePresenter;
import com.example.mikie.moviereview.presenter.ListMoviePresenter;
import com.example.mikie.moviereview.presenter.TrailerMoviePresenter;
import com.example.mikie.moviereview.presenter.PosterMoviePresenter;
import com.example.mikie.moviereview.presenter.ReviewMoviePresenter;
import com.example.mikie.moviereview.services.MovieService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by IT01 on 8/31/2017.
 */

public class MovieServiceImpl implements MovieService {
    private Context context;
    private ApiMovie api = ApiRest.retrofit().create(ApiMovie.class);
    private ListMoviePresenter listMoviePresenter;
    private CastingMoviePresenter castingMoviePresenter;
    private DetailMoviePresenter detailMoviePresenter;
    private PosterMoviePresenter posterMoviePresenter;
    private ReviewMoviePresenter reviewMoviePresenter;
    private TrailerMoviePresenter trailerMoviePresenter;
    private CollectionMoviePresenter collectionMoviePresenter;
    private ProgressDialog dialog;
    private RecyclerView.ViewHolder holder;

    public MovieServiceImpl(ListMoviePresenter listMoviePresenter, Context context) {
        this.listMoviePresenter = listMoviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(CastingMoviePresenter castingMoviePresenter, Context context) {
        this.castingMoviePresenter = castingMoviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(DetailMoviePresenter detailMoviePresenter, Context context) {
        this.detailMoviePresenter = detailMoviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(PosterMoviePresenter posterMoviePresenter, Context context) {
        this.posterMoviePresenter = posterMoviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(ReviewMoviePresenter reviewMoviePresenter, Context context) {
        this.reviewMoviePresenter = reviewMoviePresenter;
        this.context = context;
    }

    public MovieServiceImpl(TrailerMoviePresenter trailerMoviePresenter, Context context) {
        this.context = context;
        this.trailerMoviePresenter = trailerMoviePresenter;
    }

    public MovieServiceImpl(CollectionMoviePresenter collectionMoviePresenter, Context context, RecyclerView.ViewHolder holder) {
        this.context = context;
        this.collectionMoviePresenter = collectionMoviePresenter;
        this.holder = holder;
    }

    @Override
    public void loadMovie(String jenis_, String language_, final int page_, String region_) {
        dialog = new ProgressDialog(context, R.style.MyTheme);
//        dialog.setCancelable(false);
//        dialog.setProgressStyle(this.context.R.);
        dialog.show();
        listMoviePresenter.startLoading();
        Observable<ParentMovie> observable = api.getMovie(jenis_, context.getString(R.string.api_key), language_, page_, region_);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentMovie>() {
                    @Override
                    public void onCompleted() {
                        listMoviePresenter.stopLoading();
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentMovie parentMovie) {
                        if (page_ == 1) {
                            listMoviePresenter.first(parentMovie);
                        } else {
                            listMoviePresenter.next(parentMovie);
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
                        castingMoviePresenter.load(parentCastingCrew);
                    }
                });
    }

    @Override
    public void detailMovie(String id, String language, String atr) {
        detailMoviePresenter.startLoading();
        Observable<Movie> observable = api.getDetailMovie(id, this.context.getString(R.string.api_key), language, atr);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        detailMoviePresenter.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error detail movie", e.getMessage());
                    }

                    @Override
                    public void onNext(Movie movie) {
                        detailMoviePresenter.loadMovie(movie);
                    }
                });
    }

    @Override
    public void posterMovie(String id, String language, String iil) {
        Observable<ParentBackdropPoster> observable = api.getPosterMovie(id, this.context.getString(R.string.api_key), language, iil);
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
                        posterMoviePresenter.loadPoster(parentBackdropPoster);
                    }
                });
    }

    @Override
    public void reviewMovie(String id, String language, String page) {
        Observable<ParentReview> observable = api.getReview(id, this.context.getString(R.string.api_key), language, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentReview>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentReview parentReview) {
                        reviewMoviePresenter.loadReview(parentReview);
                    }
                });
    }

    @Override
    public void trailerMovie(String id, String language) {
        Observable<ParentVideo> observable = api.getVideo(id, this.context.getString(R.string.api_key), language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentVideo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error trailer", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentVideo parentVideo) {
                        trailerMoviePresenter.loadMovie(parentVideo);
                    }
                });
    }

    @Override
    public void collectionMovie(String id, String language) {
        Observable<ParentCollection> observable = api.getCollection(id, this.context.getString(R.string.api_key), language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentCollection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error collection", e.getMessage());
                    }

                    @Override
                    public void onNext(ParentCollection parentCollection) {
                        collectionMoviePresenter.loadCollection(parentCollection, holder);
                    }
                });
    }

    @Override
    public void creditMovie(String id, String language) {

    }

    @Override
    public void similarMovie(String id, String language) {

    }
}
