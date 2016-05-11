package com.star.places.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * special case of Volley's base {@link com.android.volley.Request Request} class for handling Gson requests
 *
 * @param <T>
 */
public class GsonRequest<T> extends Request<T> {

    private final Gson mGson;

    private final Class<T> mClass;

    private final Listener<T> mListener;

    /**
     * Contructor
     *
     * @param method type of request
     * @param url target server url path
     * @param modelClass Gson model class
     * @param listener success listener
     * @param errorListener error listener
     */
    public GsonRequest(int method, String url,	Class<T> modelClass, Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.mClass = modelClass;
        this.mListener = listener;
        mGson = new Gson();
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(json, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
