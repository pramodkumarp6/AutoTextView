package com.pramod.autotextview.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
/*"http://sitcomtech.in/ferrari/apicontroller/";*/
    public static final String BASE_URL = "https://ferari.ifdc.org/apicontroller/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(Context context) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityInterceptor(context))
                .readTimeout(10, TimeUnit.MINUTES)
               .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
