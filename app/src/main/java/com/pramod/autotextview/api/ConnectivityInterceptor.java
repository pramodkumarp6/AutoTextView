package com.pramod.autotextview.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {
    Handler handler = new Handler(Looper.getMainLooper());
    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(mContext)) {


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "No internet connection",Toast.LENGTH_SHORT).show();
                }
            }, 1000 );

            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}
