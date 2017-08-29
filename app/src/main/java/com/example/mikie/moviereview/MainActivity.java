package com.example.mikie.moviereview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mikie.moviereview.activity.GenreActivity;
import com.example.mikie.moviereview.activity.Main2Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @OnClick(R.id.button2)
    public void button2(){
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
}
