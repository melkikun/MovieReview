package com.example.mikie.moviereview.presenter;

import com.example.mikie.moviereview.fragment.detailmovie.Casting;
import com.example.mikie.moviereview.model.ParentCastingCrew;

import java.util.List;

/**
 * Created by IT01 on 9/6/2017.
 */

public interface CastingPresenter {
    void load(ParentCastingCrew parentCastingCrew);
}
