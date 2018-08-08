package com.mongia.gallery.data.source;

import com.mongia.gallery.model.Pictures;

public interface RemoteDataSource {

    interface PictureCallBack{
        void onResponse(Pictures pictures);
        void onErrorResponse(Throwable throwable);
    }

    void getRecentPics(PictureCallBack pictureCallBack);

    void getSearchedPics(String text, PictureCallBack pictureCallBack);
}
