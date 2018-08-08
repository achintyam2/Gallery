package com.mongia.gallery.presentation;

import com.mongia.gallery.data.GalleryRepo;
import com.mongia.gallery.data.source.RemoteDataSource;
import com.mongia.gallery.model.Pictures;

public class SearchPresenter implements Contract.SearchPresenter {

    private GalleryRepo galleryRepo;
    private Contract.SearchView view;

    public SearchPresenter(GalleryRepo galleryRepo, Contract.SearchView view) {
        this.galleryRepo = galleryRepo;
        this.view = view;
    }

    @Override
    public void getSearchedPics(String query) {
        galleryRepo.getSearchedPics(query, new RemoteDataSource.PictureCallBack() {
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
