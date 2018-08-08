package com.mongia.gallery.data.network;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkContext {

    private static final String BASE_URL = "https://api.flickr.com/services/";
    private Map<String, Object> services;
    private Retrofit retrofit;
    public static APISource apiSource;


    public void initialise() {
        services = new HashMap<>();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);


        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiSource = getService(APISource.class);
    }

    /**
     * This method will be used for getting the same instance everytime
     * To avoid creating new instance every time
     *
     * @param clazz will be added if it doesn't have it in hash map
     * @param <T>   Object
     * @return <T>
     */
    private synchronized <T> T getService(Class<T> clazz) {
        String key = clazz.getName();
        if (services.containsKey(key))
            return (T) services.get(key);
        else {
            T newClass = retrofit.create(clazz);
            services.put(key, newClass);
            return newClass;
        }
    }


}
