package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.CategoryInfo;
import com.example.mikie.moviereview.model.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/11/2017.
 */

public class HorizontalTrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Video> list = new ArrayList<>();
    private Context context;
    private final String IMG = "https://img.youtube.com/vi/";

    public HorizontalTrailerAdapter(List<Video> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_trailer, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataHolder holder1 = (DataHolder) holder;
        Glide.with(this.context)
                .load(IMG+list.get(position).getKey()+"/mqdefault.jpg")
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder1.img_trailer);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_trailer)
        ImageView img_trailer;
        public DataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
