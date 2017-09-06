package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Cast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IT01 on 9/6/2017.
 */

public class CastingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Cast> list = new ArrayList<>();
    private final String IMG = "https://image.tmdb.org/t/p/w500";

    public CastingAdapter(Context context, List<Cast> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_casting_detail, parent, false);
        return new CastingHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CastingHolder castingHolder = (CastingHolder) holder;
        castingHolder.casting_name.setText(list.get(position).getName());
        castingHolder.casting_job.setText(list.get(position).getCharacter());
        Glide.with(this.context)
                .load(IMG + list.get(position).getProfilePath())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(castingHolder.castingImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CastingHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.casting_img)
        CircleImageView castingImg;
        @BindView(R.id.casting_name)
        TextView casting_name;
        @BindView(R.id.casting_job)
        TextView casting_job;

        public CastingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
