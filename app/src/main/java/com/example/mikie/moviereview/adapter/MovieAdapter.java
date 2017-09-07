package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.GenreMovie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 8/31/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_MOVIE = 0;
    private int TYPE_LOAD = 1;
    private List<GenreMovie> genreMovieList = new ArrayList<>();
    private Context context;
    private boolean isLoading = false, isMoreDataAvailable = true;
    private final String IMG = "https://image.tmdb.org/t/p/w500";
    OnLoadMoreListener onLoadMoreListener;

    public MovieAdapter(List<GenreMovie> genreMovieList, Context context) {
        this.genreMovieList = genreMovieList;
        this.context = context;
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if (viewType == TYPE_MOVIE) {
            view = layoutInflater.inflate(R.layout.custom_genre_detail, parent, false);
            return new MovieHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.row_load, parent, false);
            return new LoadingHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && !isLoading && isMoreDataAvailable && onLoadMoreListener != null) {
            isLoading = true;
            onLoadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) == TYPE_MOVIE) {
            MovieHolder holder1 = (MovieHolder) holder;
            holder1.judul_movie.setText(genreMovieList.get(position).getTitle());
            holder1.rating.setText(genreMovieList.get(position).getVoteAverage() + "");
            Glide.with(this.context)
                    .load(IMG + genreMovieList.get(position).getPosterPath())
                    .crossFade()
                    .override(640, 480)
                    .into(holder1.poster);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (genreMovieList.get(position).getId() != null) {
            return TYPE_MOVIE;
        } else {
            return TYPE_LOAD;
        }
    }

    @Override
    public int getItemCount() {
        return genreMovieList.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.rating)
        TextView rating;
        @BindView(R.id.judul_movie)
        TextView judul_movie;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LoadingHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        public LoadingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }
}
