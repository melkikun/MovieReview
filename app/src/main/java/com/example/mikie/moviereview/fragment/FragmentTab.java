package com.example.mikie.moviereview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikie.moviereview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 8/29/2017.
 */

public class FragmentTab extends Fragment{
    @BindView(R.id.ft1)
    TextView textView;
    private int parameter;

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    public FragmentTab() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, view);
        textView.setText(this.getParameter()+"xxx");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
