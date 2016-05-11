package com.star.places;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.places.adapters.PlacesResultsViewAdapter;
import com.star.places.model.PlacesResult;
import com.star.places.utils.PlacesStarUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass displaying places results.
 * Activities that contain this fragment must implement the
 * {@link PlacesResultsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PlacesResultsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mPlacesResultsListView;
    public ArrayList<PlacesResult> mResults;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param placesResults results for selected category
     * @return A new instance of fragment PlacesResultsFragment.
     */
    public static PlacesResultsFragment newInstance(ArrayList<PlacesResult> placesResults) {
        PlacesResultsFragment fragment = new PlacesResultsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(PlacesStarUtils.PLACES_RESULTS_PARAM, placesResults);
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
        View rootView = inflater.inflate(R.layout.fragment_places_results, container, false);
        mPlacesResultsListView = (RecyclerView) rootView.findViewById(R.id.recycler_view_places_results);
        return rootView;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mResults = getArguments().getParcelableArrayList(PlacesStarUtils.PLACES_RESULTS_PARAM);
            mPlacesResultsListView.setHasFixedSize(true);

            // use a linear layout manager
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mPlacesResultsListView.setLayoutManager(layoutManager);

            PlacesResultsViewAdapter rcAdapter = new PlacesResultsViewAdapter(mResults, new PlacesResultsViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(PlacesResult selectedPlace) {
                    if (mListener != null) {
                        mListener.onPlaceSelected(selectedPlace);
                    }

                }
            });
            mPlacesResultsListView.setAdapter(rcAdapter);
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
        void onPlaceSelected(PlacesResult selectedPlace);
    }
}
