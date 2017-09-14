package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Crew;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentCollection;
import com.example.mikie.moviereview.model.ParentPerson;
import com.example.mikie.moviereview.model.PersonCrew;
import com.example.mikie.moviereview.presenter.CollectionMoviePresenter;
import com.example.mikie.moviereview.presenter.MoreFromPersonPresenter;
import com.example.mikie.moviereview.services.MovieService;
import com.example.mikie.moviereview.services.impl.MovieServiceImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT01 on 9/11/2017.
 */

public class MoreInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CollectionMoviePresenter, MoreFromPersonPresenter {
    private List<String> list = new ArrayList<>();
    private Context context;
    private HorizontalTrailerAdapter horizontalTrailerAdapter;
    private HorizontalCollectionAdapter horizontalCollectionAdapter;
    private HorizontalSimilarAdapter horizontalSimilarAdapter;
    private HorizontalAuthorAdapter horizontalAuthorAdapter;
    private Movie movie;
    private MovieService service;
    private MovieService service2;
    private String directur = "";
    private String directurID = "";
    private List<String> posterList = new ArrayList<>();
    private List<String> collectionList = new ArrayList<>();

    private final String TAG = getClass().getName();

    public MoreInfoAdapter(List<String> list, Context context, Movie movie) {
        this.list = list;
        this.context = context;
        this.movie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_layout, parent, false);
            return new RatingHolder(view);
        }
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_layout, parent, false);
            return new SummaryHolder(view);
        }
        if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_layout, parent, false);
            return new CollectionHolder(view);
        }
        if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_layout, parent, false);
            return new TrailerHolder(view);
        }
        if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_from_author, parent, false);
            return new MoreFromAuthorHolder(view);
        }
        if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_layout, parent, false);
            return new SimilarHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        for (Crew crew : movie.getParentCastingCrew().getCrew()) {
            if (crew.getJob().equals("Director")) {
                directur = crew.getName();
                directurID = crew.getId() + "";
                break;
            }
        }
        if (position == 0) {
            RatingHolder ratingHolder = (RatingHolder) holder;
        }
        if (position == 1) {

            SummaryHolder summaryHolder = (SummaryHolder) holder;
            summaryHolder.summary_text.setText(movie.getOverview());
            summaryHolder.release_date.setText("Release Date : " + movie.getReleaseDate());
            summaryHolder.dvd_release_date.setText("DVD Release Date : " + movie.getReleaseDate());
            summaryHolder.directur.setText("Directed By : " + directur);
            summaryHolder.budget.setText("Budget : " + movie.getBudget());
            summaryHolder.revenue.setText("Revenue : " + movie.getRevenue());
        }
        if (position == 2) {
            if (movie.getBelongsToCollection() != null) {
                service = new MovieServiceImpl((CollectionMoviePresenter) this, context, holder);
                service.collectionMovie(movie.getBelongsToCollection().getId() + "", "en");
            }
        }
        if (position == 3) {
            if (movie.getParentVideos().getResults().size() != 0) {
                TrailerHolder trailerHolder = (TrailerHolder) holder;
                trailerHolder.rv_trailer.setHasFixedSize(true);
                trailerHolder.rv_trailer.setItemAnimator(new DefaultItemAnimator());
                trailerHolder.rv_trailer.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                horizontalSimilarAdapter = new HorizontalSimilarAdapter(movie.getParentSimilar().getResults(), context);
                trailerHolder.rv_trailer.setAdapter(horizontalSimilarAdapter);
            }
        }
        if (position == 4) {
            service2 = new MovieServiceImpl((MoreFromPersonPresenter) this, context, holder);
            service2.moreFromPerson(directurID, "en-EN");
        }
        if (position == 5) {
            if (movie.getParentSimilar().getResults().size() != 0) {
                SimilarHolder similarHolder = (SimilarHolder) holder;
                similarHolder.rv_similar.setHasFixedSize(true);
                similarHolder.rv_similar.setItemAnimator(new DefaultItemAnimator());
                similarHolder.rv_similar.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                horizontalTrailerAdapter = new HorizontalTrailerAdapter(movie.getParentVideos().getResults(), context);
                similarHolder.rv_similar.setAdapter(horizontalTrailerAdapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void loadCollection(ParentCollection parentCollection, RecyclerView.ViewHolder holder) {
        CollectionHolder collectionHolder = (CollectionHolder) holder;

        for (int i = 0; i < parentCollection.getParts().size(); i++) {
            collectionList.add(parentCollection.getParts().get(i).getPosterPath());
        }

        collectionHolder.rv_collection.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        collectionHolder.rv_collection.setItemAnimator(new DefaultItemAnimator());
        horizontalCollectionAdapter = new HorizontalCollectionAdapter(collectionList, context);
        collectionHolder.rv_collection.setAdapter(horizontalCollectionAdapter);

    }

    @Override
    public void loadMoreFromPerson(ParentPerson parentPerson, RecyclerView.ViewHolder holder) {
        for (PersonCrew personCrew : parentPerson.getCrew()) {
            posterList.add(personCrew.getPosterPath());
        }
        Log.d("panjang list", posterList.size()+"");

        horizontalAuthorAdapter = new HorizontalAuthorAdapter(posterList, context);
        MoreFromAuthorHolder moreFromAuthorHolder = (MoreFromAuthorHolder) holder;
        moreFromAuthorHolder.rv_more_from_author.setHasFixedSize(true);
        moreFromAuthorHolder.rv_more_from_author.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        moreFromAuthorHolder.rv_more_from_author.setItemAnimator(new DefaultItemAnimator());
        moreFromAuthorHolder.rv_more_from_author.setAdapter(horizontalAuthorAdapter);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class RatingHolder extends RecyclerView.ViewHolder {
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

    public class TrailerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_trailer)
        RecyclerView rv_trailer;

        public TrailerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class MoreFromAuthorHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_more_from_author)
        RecyclerView rv_more_from_author;

        public MoreFromAuthorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class SimilarHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_similar)
        RecyclerView rv_similar;

        public SimilarHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class CollectionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_collection)
        RecyclerView rv_collection;

        public CollectionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
