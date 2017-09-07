package com.example.mikie.moviereview.services;

/**
 * Created by IT01 on 8/31/2017.
 */

public interface MovieService {
    void loadMovie(String jenis_, String language_, int page_, String region_);
    void castingMovie(String id);
    void detailMovie(String id);
    void posterMovie(String id, String language, String iil);
    void reviewMovie(String id, String language, String page);
}
