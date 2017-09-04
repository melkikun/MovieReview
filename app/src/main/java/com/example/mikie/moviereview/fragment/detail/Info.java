package com.example.mikie.moviereview.fragment.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.TestingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/4/2017.
 */

public class Info extends Fragment{
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    private TestingAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_detail_movie, container, false);
        ButterKnife.bind(this, view);
        for (int i=0; i < 1000; i++){
            list.add(i+"");
        }
        adapter = new TestingAdapter(list, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
