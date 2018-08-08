package com.mongia.gallery.data;

import com.mongia.gallery.data.source.RemoteDataSource;
import com.mongia.gallery.model.Pictures;

public class GalleryRepo implements RemoteDataSource {


    private static GalleryRepo INSTANCE = null;
    private final RemoteDataSource remoteDataSource;

    public GalleryRepo(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static GalleryRepo getInstance(RemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GalleryRepo(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getRecentPics(final PictureCallBack pictureCallBack) {
        remoteDataSource.getRecentPics(new PictureCallBack() {
            @Override
            public void onResponse(Pictures pictures) {
                pictureCallBack.onResponse(pictures);
            }

            @Override
            public void onErrorResponse(Throwable throwable) {
                pictureCallBack.onErrorResponse(throwable);
            }
        });
    }

    @Override
    public void getSearchedPics(String text, final PictureCallBack pictureCallBack) {
        remoteDataSource.getSearchedPics(text, new PictureCallBack() {
            @Override
            public void onResponse(Pictures pictures) {
                pictureCallBack.onResponse(pictures);
            }

            @Override
            public void onErrorResponse(Throwable throwable) {
                pictureCallBack.onErrorResponse(throwable);
            }
        });
    }
}
