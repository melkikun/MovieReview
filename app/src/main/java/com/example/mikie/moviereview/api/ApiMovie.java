package com.example.mikie.moviereview.api;

import com.example.mikie.moviereview.model.ListGenre;
import com.example.mikie.moviereview.model.ParentGenreDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mikie on 8/24/2017.
 */

public interface ApiMovie {
    @GET("genre/movie/list")
    Observable<ListGenre> test(@Query("api_key") String api, @Query("language")String language);
    @GET("genre/{id}/movies")
    Observable<ParentGenreDetail> detailGenreMovie(
            @Path("id") String id,
            @Query("api_key") String api,
            @Query("language") String language,
            @Query("include_adult") String adult,
            @Query("sort_by") String sort,
            @Query("page") String page
            );
}
