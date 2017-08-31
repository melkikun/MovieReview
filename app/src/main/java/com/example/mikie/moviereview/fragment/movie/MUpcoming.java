package com.example.mikie.moviereview.fragment.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;

/**
 * Created by IT01 on 8/31/2017.
 */

public class MUpcoming extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_upcoming, container, false);
        return view;
    }
}
