package com.star.places.network;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Class to be passed to
 * {@link com.android.volley.toolbox.ImageLoader#ImageLoader(com.android.volley.RequestQueue, ImageCache)
 * ImageLoader} when
 * caching is not required.
 */
class NoCache implements ImageCache {

    /**
     * Nothing to see here.
     */
    NoCache() {
        // do nothing
    }

    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        // do nothing
    }

}
