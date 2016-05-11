package com.star.places.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.places.R;
import com.star.places.model.PlacesResult;
import com.star.places.network.StarPlacesVolley;

import java.util.List;

/**
 * RecyclerView adapter handling results for selected category
 */
public class PlacesResultsViewAdapter extends RecyclerView.Adapter<PlacesResultsRecyclerViewHolder> {

    /**
     * * Interface to handle item selection
     */
    public interface OnItemClickListener {
        void onItemClick(PlacesResult item);
    }

    private List<PlacesResult> mResultsList;
    private final OnItemClickListener mListener;

    public PlacesResultsViewAdapter(List<PlacesResult> itemList, OnItemClickListener listener) {
        this.mResultsList = itemList;
        this.mListener = listener;
    }

    @Override
    public PlacesResultsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_result_item, null);
        PlacesResultsRecyclerViewHolder rcv = new PlacesResultsRecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PlacesResultsRecyclerViewHolder holder, int position) {
        String url = mResultsList.get(position).getIcon();
        holder.resultPlaceIcon.setDefaultImageResId(R.drawable.icon_location);
        holder.resultPlaceIcon.setImageUrl(url, StarPlacesVolley.getImageLoader());
        holder.resultPlaceName.setText(mResultsList.get(position).getName());
        holder.bind(mResultsList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return this.mResultsList.size();
    }
}
