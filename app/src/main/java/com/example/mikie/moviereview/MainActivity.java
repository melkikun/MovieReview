package com.example.mikie.moviereview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikie.moviereview.activity.GenreActivity;
import com.example.mikie.moviereview.api.ApiMovie;
import com.example.mikie.moviereview.api.ApiRest;
import com.example.mikie.moviereview.model.ListGenre;
import com.example.mikie.moviereview.presenter.GenrePresenter;
import com.example.mikie.moviereview.services.GenreService;
import com.example.mikie.moviereview.services.impl.GenreServicesImpl;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.genre_tv)
    public void onClickGenreTv(){
        Intent intent  = new Intent(getApplicationContext(), GenreActivity.class);
        startActivity(intent);
    }
}
