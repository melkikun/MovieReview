package com.example.mikie.moviereview.fragment.detailmovie;

import android.nfc.Tag;
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
import android.widget.TextView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.TestingAdapter;
import com.example.mikie.moviereview.model.Cast;
import com.example.mikie.moviereview.model.Crew;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentCastingCrew;
import com.example.mikie.moviereview.presenter.CastingPresenter;
import com.example.mikie.moviereview.presenter.DetailMoviePresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/4/2017.
 */

public class Info extends Fragment implements DetailMoviePresenter, CastingPresenter{
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
    private TestingAdapter adapter;
    private List<String> list = new ArrayList<>();
    private final String TAG  = getClass().getSimpleName();
    private MovieService service;
    private MovieService service2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_detail_movie, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        service = new MovieServiceImpl((DetailMoviePresenter) this, getContext());
        service.detailMovie(args.getString("data"));
        service2 = new MovieServiceImpl((CastingPresenter) this, getContext());
        service2.castingMovie(args.getString("data"));
        return view;
    }

    @Override
    public void loadMovie(Movie movie) {
        int point = movie.getOverview().indexOf(".");
        String sinopsis = movie.getOverview().substring(0, point);
        linier2_text_review.setText(sinopsis+".");
        linier2_release_date.setText(movie.getReleaseDate());
        linier2_dvd_release_date.setText(movie.getReleaseDate());
//        linier2_direktur.setText(movie.getCastingCrews().get(0).getCast().get(0).getName());
        linier2_budget.setText(movie.getBudget()+"");
        linier_2_revenue.setText(movie.getRevenue()+"");
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void load(ParentCastingCrew parentCastingCrew) {
        for (Crew crew:parentCastingCrew.getCrew()) {
                if(crew.getJob().equals("Director")){
                    Log.d("jabatan", crew.getJob().toString());
                    linier2_direktur.setText(crew.getName());
                    break;
                }
        }

    }
}
