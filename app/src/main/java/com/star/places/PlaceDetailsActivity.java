package com.star.places;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.star.places.model.PlaceLocationCoordinates;
import com.star.places.model.PlacesResult;
import com.star.places.utils.PlacesStarUtils;

/**
 * Activity displaying the selected location details
 */
public class PlaceDetailsActivity extends ActionBarActivity implements PlaceDetailsFragment.OnFragmentInteractionListener, OnMapReadyCallback {

    private static final int FIRST_FRAGMENT_INDEX = 1;

    private PlacesResult mSelectedPlace;
    private RelativeLayout mContainer;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        mContainer = (RelativeLayout) findViewById(R.id.place_details_container);
        Intent intent = getIntent();
        if (intent != null) {
            mSelectedPlace = intent.getParcelableExtra(PlacesStarUtils.SELECTED_PLACE_PARAM);
            loadDetailsFragment();
        }
    }

    private void loadDetailsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = PlaceDetailsFragment.newInstance(mSelectedPlace);
        ft.replace(R.id.place_details_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void loadLocationMapFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SupportMapFragment supportMapFragment =  SupportMapFragment.newInstance();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.zoom_out_left, R.anim.zoom_in_left, R.anim.slide_out_right);
        ft.replace(R.id.place_details_container, supportMapFragment);
        ft.addToBackStack(null);
        ft.commit();

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onSeeOnMapRequested(PlaceLocationCoordinates selectedLocation) {
        Toast.makeText(this, selectedLocation.getLat() + "," + selectedLocation.getLng(), Toast.LENGTH_LONG).show();
        loadLocationMapFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker to selected place, and move the camera.
        LatLng selectedPlaceCoord = new LatLng(Double.parseDouble(mSelectedPlace.getGeometry().getLocation().getLat()),
                Double.parseDouble(mSelectedPlace.getGeometry().getLocation().getLng()));
        mMap.addMarker(new MarkerOptions().position(selectedPlaceCoord).title(mSelectedPlace.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedPlaceCoord, PlacesStarUtils.CAMERA_ZOOM_PARAM));
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= FIRST_FRAGMENT_INDEX) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
