package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.CategoryInfo;
import com.example.mikie.moviereview.model.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/12/2017.
 */

public class HorizontalVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Video> list = new ArrayList<>();
    private Context context;

    public HorizontalVideoAdapter(List<Video> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_trailer, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class VideoHolder extends RecyclerView.ViewHolder {

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
