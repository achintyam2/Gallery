package com.mongia.gallery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Photo implements Serializable {


    @SerializedName("id")
    private String imageId;

    @SerializedName("title")
    private String imageTitle;

    @SerializedName("url_s")
    private String imageUrl;

    public String getImageId() {
        return imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
