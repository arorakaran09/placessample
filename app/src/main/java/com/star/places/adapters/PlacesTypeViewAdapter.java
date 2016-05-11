package com.star.places.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.places.R;

import java.util.List;

/**
 * RecyclerView adapter for categories of places
 */
public class PlacesTypeViewAdapter extends RecyclerView.Adapter<PlacesTypeRecyclerViewHolder> {

    /**
     * Interface to handle item selection
     */
    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    private List<String> mItemList;
    private final OnItemClickListener mListener;

    /**
     * Constructor
     *
     * @param itemList list of categories
     * @param listener listener to handle category selection
     */
    public PlacesTypeViewAdapter(List<String> itemList,  OnItemClickListener listener) {
        this.mItemList = itemList;
        this.mListener = listener;
    }

    @Override
    public PlacesTypeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_type, null);
        PlacesTypeRecyclerViewHolder rcv = new PlacesTypeRecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PlacesTypeRecyclerViewHolder holder, int position) {
        holder.categoryName.setText(mItemList.get(position));
        holder.bind(mItemList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return this.mItemList.size();
    }
}
