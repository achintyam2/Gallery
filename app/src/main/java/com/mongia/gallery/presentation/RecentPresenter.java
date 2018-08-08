package com.mongia.gallery.presentation;

import com.mongia.gallery.data.GalleryRepo;
import com.mongia.gallery.data.source.RemoteDataSource;
import com.mongia.gallery.model.Pictures;

public class RecentPresenter implements Contract.RecentPresenter {

    private GalleryRepo galleryRepo;
    private Contract.RecentView view;

    public RecentPresenter(GalleryRepo galleryRepo, Contract.RecentView view) {
        this.galleryRepo = galleryRepo;
        this.view = view;
    }

    @Override
    public void getRecentPics() {
        galleryRepo.getRecentPics(new RemoteDataSource.PictureCallBack() {
            @Override
            public void onResponse(Pictures pictures) {
                view.onResponse(pictures);
            }

            @Override
            public void onErrorResponse(Throwable throwable) {
                view.onErrorResponse(throwable);
            }
        });
    }
}
