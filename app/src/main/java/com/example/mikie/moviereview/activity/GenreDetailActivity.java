package com.example.mikie.moviereview.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.GenreDetailRecycleVievAdapter;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.Genre;
import com.example.mikie.moviereview.model.GenreDetail;
import com.example.mikie.moviereview.model.ParentGenreDetail;
import com.example.mikie.moviereview.presenter.GenreDetailPresenter;
import com.example.mikie.moviereview.services.GenreService;
import com.example.mikie.moviereview.services.impl.GenreServicesImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GenreDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private ApiMovie api = ApiRest.retrofit().create(ApiMovie.class);
    private Context context = this;
    private GenreDetailRecycleVievAdapter adapter;
    private List<GenreDetail> list = new ArrayList<>();
    private int page = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String nama = bundle.getString("judul");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(nama);
        this.context = this;
        adapter = new GenreDetailRecycleVievAdapter(context, list);
        adapter.setLoadMoreListener(new GenreDetailRecycleVievAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        loadMore(page);
                        page++;
                    }
                });
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(adapter);
        load(1);
    }


    public void load(int page) {
        Observable<ParentGenreDetail> observable = api.detailGenreMovie("37", this.getString(R.string.api_key), null, null, null, page+"");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentGenreDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ParentGenreDetail parentGenreDetail) {
                        list.addAll(parentGenreDetail.getResults());
                        adapter.notifyDataChanged();
                    }
                });
    }

    public void loadMore(int page) {
        list.add(new GenreDetail(true));
        adapter.notifyItemInserted(list.size());
        Observable<ParentGenreDetail> observable = api.detailGenreMovie("37", this.getString(R.string.api_key), null, null, null, page+"");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ParentGenreDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ParentGenreDetail parentGenreDetail) {
                        list.remove(list.size() - 1);
                        List<GenreDetail> list1 = parentGenreDetail.getResults();
                        if (list1.size() > 0) {
                            list.addAll(list1);
                        } else {
                            adapter.setMoreDataAvailable(false);
                            Toast.makeText(context, "No More Data Available", Toast.LENGTH_LONG).show();
                        }
                        adapter.notifyDataChanged();
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
