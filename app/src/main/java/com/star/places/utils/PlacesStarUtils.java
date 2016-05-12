package com.star.places.utils;

/**
 * Created by arokaran on 5/11/16.
 */
public class PlacesStarUtils {

    /**
     * Google API Key
     */
    public static final String GOOGLE_API_KEP = "AIzaSyAtI_qpkzbZt3z8fryuTKNSYTBuupXwQLM";

    /**
     * Proximity radius for fetching the places
     */
    public static final String PLACES_RADIUS_METERS = "500";

    /**
     * Key param for latitude
     */
    public static final String PLACES_LAT_KEY = "lat_key";

    /**
     * Key param for longitude
     */
    public static final String PLACES_LON_KEY = "lon_key";

    /**
     * Key param for radius
     */
    public static final String PLACES_RADIUS_KEY = "radius_key";

    /**
     * Key param for place type
     */
    public static final String PLACES_TYPE_KEY = "type_key";

    /**
     * Key param for google api
     */
    public static final String PLACES_GOOGLE_API_KEY = "api_key";

    /**
     * Key param for google api
     */
    public static final String PLACE_PHOTO_REF_KEY = "photo_ref_key";

    /**
     * Key param for user's current location
     */
    public static final String CURRENT_LOCATION_PARAM = "currentLocationParam";

    /**
     * Key param for selected category results
     */
    public static final String PLACES_RESULTS_PARAM = "selectedCatergoryResultsParam";

    /**
     * Key param for selected category results
     */
    public static final String SELECTED_PLACE_PARAM = "selectedPlaceParam";

    /**
     * Used to state that cache is not required.
     */
    public static final int NO_CACHE = 0;

    /**
     * Used to define camera zoom for location displayed on google map
     */
    public static final float CAMERA_ZOOM_PARAM = 15f;

    /**
     * returns the url to fetch photo for given reference
     *
     * @param photoApiUrl photo url link
     * @param reference reference of the image
     *
     * @return url to fetch photo from google api
     */
    public static String getPhotoUrl(String photoApiUrl, String reference) {
        String photoAPiUrl = photoApiUrl + GOOGLE_API_KEP;
        photoAPiUrl = photoAPiUrl.replace(PLACE_PHOTO_REF_KEY, reference);
        return photoAPiUrl;
    }
}
