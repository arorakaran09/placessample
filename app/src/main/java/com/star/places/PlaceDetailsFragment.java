package com.star.places;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.star.places.model.PlaceLocationCoordinates;
import com.star.places.model.PlacePhoto;
import com.star.places.model.PlacesResult;
import com.star.places.network.StarPlacesVolley;
import com.star.places.utils.PlacesStarUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass displaying the selected location details.
 * Activities that contain this fragment must implement the
 * {@link PlaceDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlaceDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceDetailsFragment extends Fragment {

    private PlacesResult mSelectedPlace;
    private PlaceLocationCoordinates mSelectedPlaceLatLon;
    private TextView mSelectedPlaceNameView;
    private NetworkImageView mSelectedPlaceImageView;
    private TextView mSelectedPlaceVicinityView;
    private Button mBtnSeeOnMap;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedPlace place selected
     * @return A new instance of fragment PlaceDetailsFragment.
     */
    public static PlaceDetailsFragment newInstance(PlacesResult selectedPlace) {
        PlaceDetailsFragment fragment = new PlaceDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PlacesStarUtils.SELECTED_PLACE_PARAM, selectedPlace);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_place_details, container, false);
        mSelectedPlaceNameView = (TextView) rootView.findViewById(R.id.place_detail_name);
        mSelectedPlaceImageView = (NetworkImageView) rootView.findViewById(R.id.place_detail_image);
        mSelectedPlaceVicinityView = (TextView) rootView.findViewById(R.id.place_detail_vicinity);
        mBtnSeeOnMap = (Button) rootView.findViewById(R.id.place_detail_btn_map);
        mBtnSeeOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMapButtonPressed();
            }
        });
        return rootView;
    }

    private void onMapButtonPressed() {
        if (mListener != null) {
            mListener.onSeeOnMapRequested(mSelectedPlaceLatLon);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mSelectedPlace = getArguments().getParcelable(PlacesStarUtils.SELECTED_PLACE_PARAM);
            if (mSelectedPlace != null) {
                mSelectedPlaceNameView.setText(mSelectedPlace.getName());
                ArrayList<PlacePhoto> photos = mSelectedPlace.getPhotos();
                if ((photos != null) && (photos.size() > 0)) {
                    mSelectedPlaceImageView.setImageUrl(PlacesStarUtils.getPhotoUrl(getResources().getString(R.string.photos_api_url),
                            photos.get(0).getPhoto_reference()), StarPlacesVolley.getImageLoader());
                }
                mSelectedPlaceVicinityView.setText(mSelectedPlace.getVicinity());
                mSelectedPlaceLatLon = mSelectedPlace.getGeometry().getLocation();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity
     */
    public interface OnFragmentInteractionListener {
        void onSeeOnMapRequested(PlaceLocationCoordinates selectedLocation);
    }
}
