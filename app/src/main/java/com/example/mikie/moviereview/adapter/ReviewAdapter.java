package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Review;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/7/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Review> list = new ArrayList<>();
    private Context context;
//    private LayoutInflater inflater;
    public ReviewAdapter(List<Review> list, Context context) {
        this.list = list;
        this.context = context;
//        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_review_detail, parent, false);
        return new ReviewHV(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReviewHV hv = (ReviewHV) holder;
        hv.review_author.setText(list.get(position).getAuthor());
        hv.review_text.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReviewHV extends RecyclerView.ViewHolder{
        @BindView(R.id.review_author)
        TextView review_author;
        @BindView(R.id.review_img)
        ImageView review_img;
        @BindView(R.id.review_text)
        TextView review_text;
        public ReviewHV(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
