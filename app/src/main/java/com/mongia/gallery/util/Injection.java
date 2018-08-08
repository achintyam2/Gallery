package com.mongia.gallery.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mongia.gallery.data.GalleryRepo;
import com.mongia.gallery.data.source.GalleryRemoteDataSource;

public class Injection {

    public static GalleryRepo provideRepository(@NonNull Context context) {
        return GalleryRepo.getInstance(GalleryRemoteDataSource.getInstance());
    }
}
