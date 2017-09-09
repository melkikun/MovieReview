package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v4.util.ArraySet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/9/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Video> set = new ArrayList<>();
    private final String YOUTUBE = "https://img.youtube.com/vi/";

    public TrailerAdapter(Context context, List<Video> set) {
        this.context = context;
        this.set = set;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_trailer, parent, false);
        return new TrailerHV(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TrailerHV trailerHV = (TrailerHV) holder;
        Glide.with(this.context).load(YOUTUBE + this.set.get(position).getKey() + "/hqdefault.jpg").crossFade().into(trailerHV.background);
        trailerHV.title_film.setText(this.set.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return set.size();
    }

    public class TrailerHV extends RecyclerView.ViewHolder {
        @BindView(R.id.ivBackground)
        ImageView background;
        @BindView(R.id.title_film)
        TextView title_film;

        public TrailerHV(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
