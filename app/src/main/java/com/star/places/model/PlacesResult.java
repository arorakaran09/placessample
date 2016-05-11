package com.star.places.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * model for a location result item
 *
 * Created by arokaran on 5/11/16.
 */
public class PlacesResult implements Parcelable {
    PlaceGeometry geometry;

    String icon;

    String name;

    ArrayList<PlacePhoto> photos;

    String vicinity;

    /**
     * returns the geometry of the location item
     *
     * @return geometry of location item
     */
    public PlaceGeometry getGeometry() {
        return geometry;
    }

    /**
     * sets the geometry of the location item
     *
     * @param geometry geometry to be set
     */
    public void setGeometry(PlaceGeometry geometry) {
        this.geometry = geometry;
    }

    /**
     * returns the icon of the location item
     *
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * sets the icon for the location item
     *
     * @param icon icon to be set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * returns the name of the location item
     *
     * @return name of location item
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the location item
     *
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns list of photos for location item
     *
     * @return list of photos
     */
    public ArrayList<PlacePhoto> getPhotos() {
        return photos;
    }

    /**
     * sets the list of photos for location item
     *
     * @param photos list of photos to be set
     */
    public void setPhotos(ArrayList<PlacePhoto> photos) {
        this.photos = photos;
    }

    /**
     * returns the vicinity for location item
     *
     * @return vicinity of the location item
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     * sets the vicinity of the location item
     *
     * @param vicinity vicinity to be set
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    // Parcelling part
    /**
     * Method implemented to read parcelled object.
     *
     * @param in
     *            object to read
     */
    public PlacesResult(Parcel in) {
        geometry = in.readParcelable(PlaceGeometry.class.getClassLoader());
        icon = in.readString();
        name = in.readString();
        photos = in.readArrayList(PlacePhoto.class.getClassLoader());
        vicinity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(geometry, flags);
        dest.writeString(icon);
        dest.writeString(name);
        dest.writeList(photos);
        dest.writeString(vicinity);
    }

    /**
     * Implemented to parcel object.
     *
     */
    public static final Parcelable.Creator<PlacesResult> CREATOR = new Parcelable.Creator<PlacesResult>() {
        @Override
        public PlacesResult createFromParcel(Parcel in) {
            return new PlacesResult(in);
        }

        @Override
        public PlacesResult[] newArray(int size) {
            return new PlacesResult[size];
        }
    };
}
