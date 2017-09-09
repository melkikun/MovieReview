package com.example.mikie.moviereview.fragment.detailmovie;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.SimilarAdapter;
import com.example.mikie.moviereview.adapter.TrailerAdapter;
import com.example.mikie.moviereview.model.Crew;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentCastingCrew;
import com.example.mikie.moviereview.model.ParentVideo;
import com.example.mikie.moviereview.model.Video;
import com.example.mikie.moviereview.presenter.CastingMoviePresenter;
import com.example.mikie.moviereview.presenter.DetailMoviePresenter;
import com.example.mikie.moviereview.presenter.TrailerMoviePresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/4/2017.
 */

public class InfoMovie extends Fragment implements DetailMoviePresenter {
    private final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    @BindView(R.id.linier2_text_review)
    TextView linier2_text_review;
    @BindView(R.id.linier2_release_date)
    TextView linier2_release_date;
    @BindView(R.id.linier2_dvd_release_date)
    TextView linier2_dvd_release_date;
    @BindView(R.id.linier2_direktur)
    TextView linier2_direktur;
    @BindView(R.id.linier2_budget)
    TextView linier2_budget;
    @BindView(R.id.linier_2_revenue)
    TextView linier_2_revenue;
    @BindView(R.id.rv_trailer)
    RecyclerView rv_trailer;
    @BindView(R.id.rv_similar)
    RecyclerView rv_similar;
    private final String TAG  = getClass().getSimpleName();
    private MovieService detailService;
    private TrailerAdapter trailerAdapter;
    private SimilarAdapter similarAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_detail_movie, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        detailService = new MovieServiceImpl(this, getContext());
        detailService.detailMovie(args.getString("data"), null, "videos,images,similar");
        return view;
    }

    @Override
    public void loadMovie(Movie movie) {
        int point = movie.getOverview().indexOf(".");
        String sinopsis = movie.getOverview().substring(0, point);
        linier2_text_review.setText(sinopsis+".");
        linier2_release_date.setText(movie.getReleaseDate());
        linier2_dvd_release_date.setText(movie.getReleaseDate());
        linier2_budget.setText(movie.getBudget()+"");
        linier_2_revenue.setText(movie.getRevenue()+"");
//        for (Crew crew:movie.getParentCastingCrew().getCrew()) {
//            if (crew.getJob() == "Director") {
//                linier2_direktur.setText(crew.getName());
//                break;
//            }
//        }

        /*Recycle view untuk video*/
        trailerAdapter = new TrailerAdapter(getContext(), movie.getParentVideos().getResults());
        rv_trailer.setHasFixedSize(true);
        rv_trailer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_trailer.setItemAnimator(new DefaultItemAnimator());
        rv_trailer.setAdapter(trailerAdapter);

        /*Recycle view untuk similar*/
        similarAdapter = new SimilarAdapter(getContext(), movie.getParentSimilar().getResults());
        rv_similar.setHasFixedSize(true);
        rv_similar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_similar.setItemAnimator(new DefaultItemAnimator());
        rv_similar.setAdapter(similarAdapter);

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
