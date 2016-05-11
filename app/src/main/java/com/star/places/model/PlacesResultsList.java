package com.star.places.model;

import java.util.ArrayList;

/**
 * data model for places results
 *
 * Created by arokaran on 5/11/16.
 */
public class PlacesResultsList {

    ArrayList<PlacesResult> results;

    /**
     * returns the places results
     *
     * @return list of places results
     */
    public ArrayList<PlacesResult> getResults() {
        return results;
    }

    /**
     * sets the results
     *
     * @param results set the results
     */
    public void setResults(ArrayList<PlacesResult> results) {
        this.results = results;
    }

}
