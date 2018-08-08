package com.mongia.gallery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Photos implements Serializable {

    @SerializedName("photo")
    private List<Photo> photoList;

    public List<Photo> getPhotoList() {
        return photoList;
    }
}
