package com.star.places.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.star.places.R;

/**
 * Holder for places categories adapter
 */
public class PlacesTypeRecyclerViewHolder extends RecyclerView.ViewHolder {

    /**
     * View displaying name of the category
     */
    public TextView categoryName;

    /**
     * View displaying icon of the category
     */
    public ImageView categoryIcon;

    /**
     * Constructor
     *
     * @param itemView category item view
     */
    public PlacesTypeRecyclerViewHolder(View itemView) {
        super(itemView);
        categoryName = (TextView)itemView.findViewById(R.id.place_type_title);
        categoryIcon = (ImageView)itemView.findViewById(R.id.place_type_icon);
    }

    /**
     * Method binds listener for category item
     *
     * @param item category item
     * @param listener listener to listen for click events for selected category
     */
    public void bind(final String item, final PlacesTypeViewAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
}