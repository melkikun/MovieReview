package com.example.mikie.moviereview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by IT01 on 9/9/2017.
 */

public class ParentPerson {
    @SerializedName("cast")
    @Expose
    private List<PersonCast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<PersonCrew> crew = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<PersonCast> getCast() {
        return cast;
    }

    public void setCast(List<PersonCast> cast) {
        this.cast = cast;
    }

    public List<PersonCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonCrew> crew) {
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
