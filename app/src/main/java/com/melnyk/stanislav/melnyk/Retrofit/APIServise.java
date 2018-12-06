package com.melnyk.stanislav.melnyk.Retrofit;

import com.melnyk.stanislav.melnyk.Entity.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServise {

    @GET("new")
    Call<Result> getBools();
}