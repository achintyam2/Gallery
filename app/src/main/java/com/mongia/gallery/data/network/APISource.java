package com.mongia.gallery.data.network;

import com.mongia.gallery.model.Pictures;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APISource {

    @GET("rest")
    Call<Pictures> getRecentPhotos(@Query("method") String method,
                                   @Query("api_key") String key,
                                   @Query("format") String format,
                                   @Query("nojsoncallback") String callback,
                                   @Query("extras") String extras);

    @GET("rest")
    Call<Pictures> getSearchedPhotos(@Query("method") String method,
                           @Query("api_key") String key,
                           @Query("format") String format,
                           @Query("nojsoncallback") String callback,
                           @Query("extras") String extras,
                           @Query("text") String text);

}
