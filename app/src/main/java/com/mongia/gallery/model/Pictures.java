package com.mongia.gallery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pictures implements Serializable {

    @SerializedName("photos")
    private Photos photos;

    @SerializedName("stat")
    private String status;

    public Photos getPhotos() {
        return photos;
    }

    public String getStatus() {
        return status;
    }
}
