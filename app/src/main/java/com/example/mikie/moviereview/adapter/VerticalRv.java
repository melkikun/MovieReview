package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.CategoryInfo;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/11/2017.
 */

public class VerticalRv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CategoryInfo> list = new ArrayList<>();
    private Context context;
    private HorizontalTrailer horizontalTrailer;
    private HorizontalVideoAdapter horizontalVideoAdapter;
    private Movie movie;

    public VerticalRv(List<CategoryInfo> list, Context context, Movie movie) {
        this.list = list;
        this.context = context;
        this.movie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_layout, parent, false);
            return new RatingHolder(view);
        } else if(viewType == 1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_layout, parent, false);
            return new SummaryHolder(view);
        }else if(viewType == 2){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_layout, parent, false);
            return new TrailerHolder(view);
        }else if(viewType == 3){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_from_author, parent, false);
            return new MoreFromAuthorHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_layout, parent, false);
            return new SimilarHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            RatingHolder ratingHolder = (RatingHolder) holder;
            ratingHolder.text_vertical.setText(list.get(position).getTitle());
        } else if(position ==  1){
            SummaryHolder summaryHolder = (SummaryHolder) holder;
            summaryHolder.summary_text.setText("text untuk summary");
        }else if(position == 2){
            Toast.makeText(context, movie.getParentVideos().getResults().toString()+"xxxxx", Toast.LENGTH_SHORT).show();
            horizontalVideoAdapter = new HorizontalVideoAdapter(movie.getParentVideos().getResults(), this.context);
            TrailerHolder trailerHolder = (TrailerHolder) holder;
            trailerHolder.rv_trailer.setHasFixedSize(true);
            trailerHolder.rv_trailer.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            trailerHolder.rv_trailer.setItemAnimator(new DefaultItemAnimator());
            trailerHolder.rv_trailer.setAdapter(horizontalVideoAdapter);
        }else if(position == 3){

            CategoryInfo info = new CategoryInfo("1");
            CategoryInfo info2 = new CategoryInfo("2");
            CategoryInfo info3 = new CategoryInfo("3");
            CategoryInfo info4 = new CategoryInfo("4");
            CategoryInfo info5 = new CategoryInfo("5");
            List<CategoryInfo> lc = new ArrayList<>();
            lc.add(info);
            lc.add(info2);
            lc.add(info3);
            lc.add(info4);
            lc.add(info5);
            horizontalTrailer = new HorizontalTrailer(lc,context);
            MoreFromAuthorHolder moreFromAuthorHolder = (MoreFromAuthorHolder) holder;
            moreFromAuthorHolder.rv_more_from_author.setHasFixedSize(true);
            moreFromAuthorHolder.rv_more_from_author.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            moreFromAuthorHolder.rv_more_from_author.setItemAnimator(new DefaultItemAnimator());
            moreFromAuthorHolder.rv_more_from_author.setAdapter(horizontalTrailer);
        }else{
            SimilarHolder similarHolder = (SimilarHolder) holder;
            CategoryInfo info = new CategoryInfo("1");
            CategoryInfo info2 = new CategoryInfo("2");
            CategoryInfo info3 = new CategoryInfo("3");
            CategoryInfo info4 = new CategoryInfo("4");
            CategoryInfo info5 = new CategoryInfo("5");
            List<CategoryInfo> lc = new ArrayList<>();
            lc.add(info);
            lc.add(info2);
            lc.add(info3);
            lc.add(info4);
            lc.add(info5);
            horizontalTrailer = new HorizontalTrailer(lc,context);
            similarHolder.rv_similar.setHasFixedSize(true);
            similarHolder.rv_similar.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            similarHolder.rv_similar.setItemAnimator(new DefaultItemAnimator());
            similarHolder.rv_similar.setAdapter(horizontalTrailer);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RatingHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_vertical)
        TextView text_vertical;

        public RatingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class SummaryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.summary_text)
        TextView summary_text;
        @BindView(R.id.release_date)
        TextView release_date;
        @BindView(R.id.dvd_release_date)
        TextView dvd_release_date;
        @BindView(R.id.directur)
        TextView directur;
        @BindView(R.id.budget)
        TextView budget;
        @BindView(R.id.revenue)
        TextView revenue;


        public SummaryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TrailerHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_trailer)
        RecyclerView rv_trailer;
        public TrailerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class MoreFromAuthorHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_more_from_author)
        RecyclerView rv_more_from_author;
        public MoreFromAuthorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class SimilarHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_similar)
        RecyclerView rv_similar;
        public SimilarHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
