package com.mongia.gallery.data.source;


import android.support.annotation.NonNull;

import com.mongia.gallery.data.network.NetworkContext;
import com.mongia.gallery.model.Pictures;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryRemoteDataSource implements RemoteDataSource{

    private static volatile GalleryRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private GalleryRemoteDataSource() {}

    public static GalleryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (GalleryRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GalleryRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getRecentPics(final PictureCallBack pictureCallBack) {
        Call<Pictures> call = NetworkContext.apiSource.getRecentPhotos("flickr.photos.getRecent",
                "6f102c62f41998d151e5a1b48713cf13","json","1","url_s");
        call.enqueue(new Callback<Pictures>() {
            @Override
            public void onResponse(@NonNull Call<Pictures> call,@NonNull Response<Pictures> response) {
                pictureCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Pictures> call,@NonNull Throwable t) {
                pictureCallBack.onErrorResponse(t);
            }
        });
    }

    @Override
    public void getSearchedPics(String text, final PictureCallBack pictureCallBack) {
        Call<Pictures> call = NetworkContext.apiSource.getSearchedPhotos("flickr.photos.search",
                "6f102c62f41998d151e5a1b48713cf13","json","1","url_s",text);
        call.enqueue(new Callback<Pictures>() {
            @Override
            public void onResponse(@NonNull Call<Pictures> call,@NonNull Response<Pictures> response) {
                pictureCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Pictures> call,@NonNull Throwable t) {
                pictureCallBack.onErrorResponse(t);
            }
        });
    }
}
