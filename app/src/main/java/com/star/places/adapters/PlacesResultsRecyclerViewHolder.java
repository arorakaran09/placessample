package com.star.places.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.star.places.R;
import com.star.places.model.PlacesResult;

/**
 * Holder for selected category results adapter
 */
public class PlacesResultsRecyclerViewHolder extends RecyclerView.ViewHolder {

    /**
     * icon for place item
     */
    public NetworkImageView resultPlaceIcon;

    /**
     * name of place item
     */
    public TextView resultPlaceName;

    /**
     * Constructor
     *
     * @param itemView selected location item view
     */
    public PlacesResultsRecyclerViewHolder(View itemView) {
        super(itemView);
        resultPlaceIcon = (NetworkImageView)itemView.findViewById(R.id.place_icon);
        resultPlaceName = (TextView)itemView.findViewById(R.id.place_name);
    }

    /**
     * Method binds listener for category item
     *
     * @param item selected location item
     * @param listener listener to listen for click events for selected location
     */
    public void bind(final PlacesResult item, final PlacesResultsViewAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
}