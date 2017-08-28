package com.example.mikie.moviereview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.adapter.GenreAdapter;
import com.example.mikie.moviereview.model.Genre;
import com.example.mikie.moviereview.model.ListGenre;
import com.example.mikie.moviereview.presenter.GenrePresenter;
import com.example.mikie.moviereview.services.GenreService;
import com.example.mikie.moviereview.services.impl.GenreServicesImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreActivity extends AppCompatActivity implements GenrePresenter{
    @BindView(R.id.grid_genre)
    GridView gridGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        ButterKnife.bind(this);
        GenreService genreService = new GenreServicesImpl(this, getApplicationContext());
        genreService.movie();
    }

    @Override
    public void success(final ListGenre listGenre) {
        GenreAdapter genreAdapter = new GenreAdapter(getApplicationContext(), listGenre.getGenres());
        gridGenre.setAdapter(genreAdapter);
        gridGenre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), GenreDetailActivity.class);
                intent.putExtra("judul", listGenre.getGenres().get(position).getName().toString());
                intent.putExtra("id", listGenre.getGenres().get(position).getId().toString());
                startActivity(intent);
            }
        });
    }
}
