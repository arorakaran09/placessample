package com.star.places.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;
import com.star.places.utils.PlacesStarUtils;

/**
 * Helper class that is used to provide references to initialized RequestQueue(s) and ImageLoader(s)
 *
 */
public class StarPlacesVolley {

    private static RequestQueue mRequestQueue;

    /**
     * Volley image loader.
     */
    private static ImageLoader mImageLoader;

    private StarPlacesVolley() {
        // no multiple instances
    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        // Use 1/8th of the available memory for this memory cache.
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
    }

    /**
     * Initializer for the manager. Must be called prior to use.
     *
     * @param context
     *            context of application
     * @param cacheSize
     *            max size for the cache
     */
    public static void init(Context context, int cacheSize) {
        mRequestQueue = Volley.newRequestQueue(context);
        ImageCache bitmapCache;

        if (cacheSize > 0) {
            bitmapCache = new BitmapLruCache(cacheSize);
        } else if (cacheSize == PlacesStarUtils.NO_CACHE) {
            bitmapCache = new NoCache();
        } else {
            bitmapCache = new BitmapLruCache();
        }
        mImageLoader = new ImageLoader(mRequestQueue, bitmapCache);
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
     * that no memory caching is used. This is useful for images that you know that will be show
     * only once.
     *
     * @return
     */
    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}

