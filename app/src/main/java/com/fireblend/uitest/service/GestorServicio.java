package com.fireblend.uitest.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestorServicio {

    //Variable singleton privada, solo accesible por medio del
    private static final String baseUrl = "https://jsonplaceholder.typicode.com/";
    private static final Retrofit jsonPlaceHolderInstance = new Retrofit.Builder()
            .baseUrl(baseUrl)
            //Se especifica el serializados a usarse (en este caso gson para json)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static ServicioPosts singletonPosts;
    private static ServicioPhotos singletonPhotos;

    public static ServicioPosts obtenerServicioPosts() {
        if(singletonPosts == null) {
            singletonPosts = jsonPlaceHolderInstance.create(ServicioPosts.class);
        }
        return singletonPosts;
    }

    public static ServicioPhotos obtenerServicioPhotos() {
        if(singletonPhotos == null) {
            singletonPhotos = jsonPlaceHolderInstance.create(ServicioPhotos.class);
        }

        return singletonPhotos;
    }

}
