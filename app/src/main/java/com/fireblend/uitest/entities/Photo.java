package com.fireblend.uitest.entities;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    public int photoId;
    public int albumId;
    public String title;
    public String url;
    public String thumbnailUrl;

    public Photo(String title, String url, String thumbnailUrl, int id, int albumId) {
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.photoId = id;
        this.albumId = albumId;
    }
}
