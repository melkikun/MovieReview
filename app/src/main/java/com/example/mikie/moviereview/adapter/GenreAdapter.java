package com.example.mikie.moviereview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mikie.moviereview.R;
import com.example.mikie.moviereview.model.Genre;
import com.example.mikie.moviereview.model.ListGenre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikie on 8/24/2017.
 */

public class GenreAdapter extends BaseAdapter{
    private Context context;
    private List<Genre> genreList = new ArrayList<>();

    public GenreAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
    }

    @Override
    public int getCount() {
        return genreList.size();
    }

    @Override
    public Object getItem(int position) {
        return genreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_genre, null);
        TextView textView = (TextView) view.findViewById(R.id.custom_text);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linier_genre);
        textView.setText(genreList.get(position).getName().toString());
        linearLayout.setBackgroundColor(Color.LTGRAY);
        return view;
    }
}
