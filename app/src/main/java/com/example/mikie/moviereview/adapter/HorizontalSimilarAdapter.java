package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Similar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/14/2017.
 */

public class HorizontalSimilarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Similar> list = new ArrayList<>();
    private Context context;
    private final String IMG = "https://image.tmdb.org/t/p/w500";

    public HorizontalSimilarAdapter(List<Similar> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataHolder dataHolder = (DataHolder) holder;
        Glide.with(this.context)
                .load(IMG+list.get(position).getPosterPath())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dataHolder.img_trailer);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_trailer)
        ImageView img_trailer;
        public DataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
