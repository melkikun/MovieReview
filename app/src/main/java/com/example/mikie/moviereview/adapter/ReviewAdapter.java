package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.mikie.moviereview.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IT01 on 9/7/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Review> list = new ArrayList<>();
    private Context context;

    public ReviewAdapter(List<Review> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
