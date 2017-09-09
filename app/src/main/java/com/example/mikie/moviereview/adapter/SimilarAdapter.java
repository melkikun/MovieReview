package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Similar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/9/2017.
 */

public class SimilarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<Similar> similars = new ArrayList<>();
    private final String IMG = "https://image.tmdb.org/t/p/w500";
    public SimilarAdapter(Context context, List<Similar> similars) {
        this.context = context;
        this.similars = similars;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_similar, parent, false);
        return new SimilarHV(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimilarHV hv = (SimilarHV) holder;
        hv.similar_text.setText(similars.get(position).getTitle());
        Glide.with(context).load(IMG+similars.get(position).getPosterPath()).crossFade().into(hv.similar_image);
    }

    @Override
    public int getItemCount() {
        return similars.size();
    }

    public class SimilarHV extends RecyclerView.ViewHolder{
        @BindView(R.id.similar_image)
        ImageView similar_image;
        @BindView(R.id.similar_text)
        TextView similar_text;
        public SimilarHV(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
