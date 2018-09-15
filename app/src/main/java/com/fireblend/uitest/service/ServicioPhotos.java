package com.fireblend.uitest.service;

import com.fireblend.uitest.entities.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioPhotos {
    @GET("photos")
    Call<List<Photo>> getAllPhotos();
}
