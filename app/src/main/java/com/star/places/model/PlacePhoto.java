package com.star.places.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * model for photo item of the location
 *
 * Created by arokaran on 5/11/16.
 */
public class PlacePhoto implements Parcelable {

    String photo_reference;

    /**
     * returns the photo reference for the location
     *
     * @return photo reference
     */
    public String getPhoto_reference() {
        return photo_reference;
    }

    /**
     * sets the photo reference for the location
     *
     * @param photo_reference photo reference to be set
     */
    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    // Parcelling part
    /**
     * Method implemented to read parcelled object.
     *
     * @param in
     *            object to read
     */
    public PlacePhoto(Parcel in) {
        photo_reference = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photo_reference);
    }

    /**
     * Implemented to parcel object.
     *
     */
    public static final Parcelable.Creator<PlacePhoto> CREATOR = new Parcelable.Creator<PlacePhoto>() {
        @Override
        public PlacePhoto createFromParcel(Parcel in) {
            return new PlacePhoto(in);
        }

        @Override
        public PlacePhoto[] newArray(int size) {
            return new PlacePhoto[size];
        }
    };
}
