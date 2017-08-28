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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.GenreDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 8/25/2017.
 */

public class GenreDetailRecycleVievAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_MOVIE = 0;
    private final int TYPE_LOAD = 1;
    private Context context;
    private List<GenreDetail> list = new ArrayList<>();
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;
    private final String IMG = "https://image.tmdb.org/t/p/w500";

    public GenreDetailRecycleVievAdapter(Context context, List<GenreDetail> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_LOAD) {
            View view = inflater.inflate(R.layout.row_load, parent, false);
            return new LoadHolder(view);
        } else {
            View view = inflater.inflate(R.layout.custom_genre_detail, parent, false);
            return new MovieHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) == TYPE_MOVIE) {
            MovieHolder holder1 = (MovieHolder) holder;
            if (list.get(position).getPosterPath() == null) {
                holder1.poster.setImageResource(R.drawable.ic_image_testing);
            } else {
                Glide.with(context).load(IMG + list.get(position).getPosterPath()).thumbnail(0.5f).crossFade().into(holder1.poster);
            }
            holder1.rating.setText(list.get(position).getVoteAverage() + "");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getId() != null) {
            return TYPE_MOVIE;
        } else {
            return TYPE_LOAD;
        }
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.rating)
        TextView rating;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LoadHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        public LoadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }
}
