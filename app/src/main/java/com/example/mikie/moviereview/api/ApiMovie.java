package com.example.mikie.moviereview.api;

import com.example.mikie.moviereview.model.ListGenre;
import com.example.mikie.moviereview.model.Movie;
import com.example.mikie.moviereview.model.ParentBackdropPoster;
import com.example.mikie.moviereview.model.ParentCastingCrew;
import com.example.mikie.moviereview.model.ParentGenreDetail;
import com.example.mikie.moviereview.model.ParentMovie;
import com.example.mikie.moviereview.model.ParentReview;
import com.example.mikie.moviereview.model.Poster;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mikie on 8/24/2017.
 */

public interface ApiMovie {

    @GET("movie/{jenis}")
    Observable<ParentMovie> getMovie(
            @Path("jenis") String jenis,
            @Query("api_key") String jenis_api,
            @Query("language") String jenis_language,
            @Query("page") int jenis_page,
            @Query("region") String jenis_region
    );

    @GET("movie/{id}/credits")
    Observable<ParentCastingCrew> getCasting(
        @Path("id") String id,
        @Query("api_key") String api_key
    );

    @GET("movie/{id}")
    Observable <Movie> getDetailMovie(
            @Path("id") String id,
            @Query("api_key") String api,
            @Query("language") String language,
            @Query("append_to_response") String append

    );

    @GET("movie/{id}/images")
    Observable<ParentBackdropPoster> getPosterMovie(
            @Path("id") String id,
            @Query("api_key") String api,
            @Query("language") String language,
            @Query("include_image_language") String iil
    );

    @GET("movie/{id}/reviews")
    Observable<ParentReview> getReview(
            @Path("id") String id,
            @Query("api_key") String api,
            @Query("language") String language,
            @Query("page") String iil
    );


}
