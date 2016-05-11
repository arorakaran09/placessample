package com.star.places;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.star.places.model.PlacesResult;
import com.star.places.utils.PlacesStarUtils;

import java.util.ArrayList;

/**
 * Activity displaying the results for selected category
 */
public class PlacesResultsActivity extends ActionBarActivity implements PlacesResultsFragment.OnFragmentInteractionListener {

    public ArrayList<PlacesResult> mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_results);

        Intent intent = getIntent();
        if (intent != null) {
            mResults = intent.getParcelableArrayListExtra(PlacesStarUtils.PLACES_RESULTS_PARAM);
            loadCategoriesFragment();
        }
    }

    private void loadCategoriesFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = PlacesResultsFragment.newInstance(mResults);
        ft.replace(R.id.place_results_container, fragment);
        ft.commit();
    }

    @Override
    public void onPlaceSelected(PlacesResult selectedPlace) {
        Intent intent = new Intent(this, PlaceDetailsActivity.class);
        intent.putExtra(PlacesStarUtils.SELECTED_PLACE_PARAM, selectedPlace);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
