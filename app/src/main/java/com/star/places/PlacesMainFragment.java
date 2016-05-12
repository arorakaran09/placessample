package com.star.places;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.star.places.adapters.PlacesTypeViewAdapter;
import com.star.places.model.PlacesResultsList;
import com.star.places.network.GsonRequest;
import com.star.places.network.StarPlacesVolley;
import com.star.places.utils.PlacesStarUtils;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass displaying categories of places.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PlacesMainFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mPlacesTypeGridView;
    private GridLayoutManager mPlacesTypeGridLayout;
    private ProgressDialog mProgressDialog;
    private Location mCurrentLocation;

    private Response.Listener<PlacesResultsList> responseListener = new Response.Listener<PlacesResultsList>() {
        @Override
        public void onResponse(PlacesResultsList response) {
            mProgressDialog.dismiss();
            if (mListener != null) {
                mListener.onPlacesTypeSelected(response);
            }
        }
    };

    private Response.ErrorListener errorListener =  new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mProgressDialog.dismiss();
            Toast.makeText(getActivity(), "Could not fetch places for selected category, Please check internet connection", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param location current user location
     * @return A new instance of fragment PlacesMainFragment.
     */
    public static PlacesMainFragment newInstance(Location location) {
        PlacesMainFragment fragment = new PlacesMainFragment();
        Bundle args = new Bundle();
        args.putParcelable(PlacesStarUtils.CURRENT_LOCATION_PARAM, location);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_places_home_page, container, false);
        mPlacesTypeGridView = (RecyclerView) rootView.findViewById(R.id.recycler_view_places_types);
        return rootView;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mCurrentLocation = getArguments().getParcelable(PlacesStarUtils.CURRENT_LOCATION_PARAM);
            List<String> rowListItem = getAllItemList();
            mPlacesTypeGridLayout = new GridLayoutManager(getActivity(), 4);

            mPlacesTypeGridView.setHasFixedSize(true);
            mPlacesTypeGridView.setLayoutManager(mPlacesTypeGridLayout);

            PlacesTypeViewAdapter rcAdapter = new PlacesTypeViewAdapter(rowListItem, new PlacesTypeViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String item) {
                    if (mCurrentLocation != null) {
                        loadPlacesData(item);
                    } else {
                        Toast.makeText(getActivity(), "Waiting for location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mPlacesTypeGridView.setAdapter(rcAdapter);
        }
    }

    private void loadPlacesData(String type) {
        String placesApiUrl = getResources().getString(R.string.places_api_url);
        placesApiUrl = placesApiUrl
                .replace(PlacesStarUtils.PLACES_LAT_KEY, String.valueOf(mCurrentLocation.getLatitude()))
                .replace(PlacesStarUtils.PLACES_LON_KEY, String.valueOf(mCurrentLocation.getLongitude()))
                .replace(PlacesStarUtils.PLACES_RADIUS_KEY, PlacesStarUtils.PLACES_RADIUS_METERS)
                .replace(PlacesStarUtils.PLACES_TYPE_KEY, type)
                .replace(PlacesStarUtils.PLACES_GOOGLE_API_KEY, PlacesStarUtils.GOOGLE_API_KEP);
        mProgressDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.progress_dialog_title), getResources().getString(R.string.progress_dialog_msg));
        GsonRequest<PlacesResultsList> gsonRequest = new GsonRequest<PlacesResultsList>(Request.Method.GET, placesApiUrl, PlacesResultsList.class,
                responseListener, errorListener);
        StarPlacesVolley.getRequestQueue().add(gsonRequest);
    }

    void setCurrentLocation(Location currentLocation) {
        mCurrentLocation = currentLocation;
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
        void onPlacesTypeSelected(PlacesResultsList response);
    }

    private List<String> getAllItemList(){
        return Arrays.asList(getResources().getStringArray(R.array.Types));
    }
}
