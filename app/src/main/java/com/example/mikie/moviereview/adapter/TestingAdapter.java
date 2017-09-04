package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikie.moviereview.R;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/4/2017.
 */

public class TestingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> list =  new ArrayList<>();
    private Context context;

    public TestingAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testing_layout, parent, false);
        return new TestingHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TestingHolder holder1 = (TestingHolder) holder;
        holder1.textView.setText("1212");
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class TestingHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text)
        TextView textView;
        public TestingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
