package com.wisdomlanna.www.dagger2_mvp_example.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public class Utils {

    private Context context;

    @Inject
    public Utils(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }

        return false;
    }
}
