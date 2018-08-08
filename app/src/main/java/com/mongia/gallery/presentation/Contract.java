package com.mongia.gallery.presentation;

import com.mongia.gallery.model.Pictures;

public interface Contract {

    interface RecentView {
        void onResponse(Pictures pictures);
        void onErrorResponse(Throwable throwable);
    }

    interface RecentPresenter{
        void getRecentPics();
    }

    interface SearchView {
        void onResponse(Pictures pictures);
        void onErrorResponse(Throwable throwable);
    }

    interface SearchPresenter{
        void getSearchedPics(String query);
    }
}
