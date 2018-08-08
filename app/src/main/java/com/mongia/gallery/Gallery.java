package com.mongia.gallery;

import android.app.Application;
import android.content.Context;

import com.mongia.gallery.data.network.NetworkContext;

public class Gallery extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        new NetworkContext().initialise();
    }
}
