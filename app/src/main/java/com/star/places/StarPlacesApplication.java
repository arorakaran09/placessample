package com.star.places;

import android.app.Application;

import com.star.places.network.StarPlacesVolley;

/**
 * Created by arokaran on 5/11/16.
 */
public class StarPlacesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize Volley for network calls
        StarPlacesVolley.init(this);
    }
}
