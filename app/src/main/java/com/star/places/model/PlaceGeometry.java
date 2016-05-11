package com.star.places.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * model for geometry of the location
 *
 * Created by arokaran on 5/11/16.
 */
public class PlaceGeometry implements Parcelable {

    PlaceLocationCoordinates location;

    /**
     * returns the geometry for the location
     *
     * @return geometry for the location
     */
    public PlaceLocationCoordinates getLocation() {
        return location;
    }

    /**
     * sets the geometry for the location
     *
     * @param location geometry to be set
     */
    public void setLocation(PlaceLocationCoordinates location) {
        this.location = location;
    }

    // Parcelling part
    /**
     * Method implemented to read parcelled object.
     *
     * @param in
     *            object to read
     */
    public PlaceGeometry(Parcel in) {
        location = in.readParcelable(PlaceLocationCoordinates.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(location, flags);
    }

    /**
     * Implemented to parcel object.
     *
     */
    public static final Parcelable.Creator<PlaceGeometry> CREATOR = new Parcelable.Creator<PlaceGeometry>() {
        @Override
        public PlaceGeometry createFromParcel(Parcel in) {
            return new PlaceGeometry(in);
        }

        @Override
        public PlaceGeometry[] newArray(int size) {
            return new PlaceGeometry[size];
        }
    };
}
