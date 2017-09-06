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

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.CastingAdapter;
import com.example.mikie.moviereview.custom.DividerItemDecoration;
import com.example.mikie.moviereview.model.Cast;
import com.example.mikie.moviereview.model.ParentCastingCrew;
import com.example.mikie.moviereview.presenter.CastingPresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/6/2017.
 */

public class Casting extends Fragment implements CastingPresenter{
    @BindView(R.id.rv_casting)
    RecyclerView recyclerView;
    private CastingAdapter castingAdapter;
    private MovieService service;
    private final String TAG = getClass().getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.casting_detail, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        Log.d(TAG, args+"");
        service = new MovieServiceImpl(this, getContext());
        service.movieCasting(args.getString("data"));
        return view;
    }

    @Override
    public void load(ParentCastingCrew parentCastingCrew) {
        castingAdapter = new CastingAdapter(getContext(), parentCastingCrew.getCast());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(castingAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }
}
