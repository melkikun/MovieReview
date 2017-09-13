package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.BelongsToCollection;
import com.example.mikie.moviereview.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/13/2017.
 */

public class HorizontalCollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> list = new ArrayList<>();
    private Context context;
    private final String IMG = "https://image.tmdb.org/t/p/w500";

    public HorizontalCollectionAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_trailer, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectionHolder collectionHolder = (CollectionHolder) holder;
        if (list.size() == 0){
            collectionHolder.img_trailer.setImageResource(R.drawable.no_image);
        }else{
            Glide.with(context)
                    .load(IMG+list.get(position))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(collectionHolder.img_trailer);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CollectionHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_trailer)
        ImageView img_trailer;
        public CollectionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
