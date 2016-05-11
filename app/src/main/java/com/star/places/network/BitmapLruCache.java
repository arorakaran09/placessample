/**
 * Name	  : BitmapLruCache.java
 * Version: 1.0
 * Date   : Oct 24, 2013
 * Copyright (c) 2013 Accenture. All rights reserved.
 */

package com.star.places.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * LRU Memory cache to be passed to
 * {@link com.android.volley.toolbox.ImageLoader#ImageLoader(com.android.volley.RequestQueue, ImageCache)
 * ImageLoader}. Used to cache downloaded images.
 *
 */
class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {

    private static final int CONVERSION_FACTOR_TO_KB = 1024;

    private static final int CONVERSION_FACTOR_TO_BYTE = 4;

    /**
     * Constructor.
     *
     */
    BitmapLruCache() {
        super(getDefaultLruCacheSize());
    }

    /**
     * Constructor.
     *
     * @param maxSize
     *            maximum size in KBs that cache may take in memory
     */
    BitmapLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        int size = (value.getRowBytes() * value.getHeight()) / CONVERSION_FACTOR_TO_KB;
        return size;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    private static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / CONVERSION_FACTOR_TO_KB);
        final int cacheSize = maxMemory / CONVERSION_FACTOR_TO_BYTE;

        return cacheSize;
    }
}
