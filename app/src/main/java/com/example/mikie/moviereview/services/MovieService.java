package com.example.mikie.moviereview.services;

/**
 * Created by IT01 on 8/31/2017.
 */

public interface MovieService {
    void loadMovie(String jenis_, String language_, int page_, String region_);
    void castingMovie(String id);
    void detailMovie(String id, String language, String atr);
    void posterMovie(String id, String language, String iil);
    void reviewMovie(String id, String language, String page);
    void trailerMovie(String id, String language);
    void collectionMovie(String id, String language);
    void creditMovie(String id, String language);
    void similarMovie(String id, String language);
    void moreFromPerson(String id, String language);
}
