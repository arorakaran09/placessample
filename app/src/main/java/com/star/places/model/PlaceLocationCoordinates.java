package com.star.places.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for geo co-ordinates of the location
 *
 * Created by arokaran on 5/11/16.
 */
public class PlaceLocationCoordinates implements Parcelable {

    String lat;

    String lng;

    /**
     * returns the longitude of the location
     *
     * @return longitude of the location
     */
    public String getLng() {
        return lng;
    }

    /**
     * sets the longitude of the location
     *
     * @param lng longitude to be set
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * returns the latitude of the location
     *
     * @return latitude to be set
     */
    public String getLat() {
        return lat;
    }

    /**
     * sets the latitude of the location
     *
     * @param lat latitude to be set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    // Parcelling part
    /**
     * Method implemented to read parcelled object.
     *
     * @param in
     *            object to read
     */
    public PlaceLocationCoordinates(Parcel in) {
        lat = in.readString();
        lng = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lat);
        dest.writeString(lng);
    }

    /**
     * Implemented to parcel object.
     *
     */
    public static final Parcelable.Creator<PlaceLocationCoordinates> CREATOR = new Parcelable.Creator<PlaceLocationCoordinates>() {
        @Override
        public PlaceLocationCoordinates createFromParcel(Parcel in) {
            return new PlaceLocationCoordinates(in);
        }

        @Override
        public PlaceLocationCoordinates[] newArray(int size) {
            return new PlaceLocationCoordinates[size];
        }
    };
}
