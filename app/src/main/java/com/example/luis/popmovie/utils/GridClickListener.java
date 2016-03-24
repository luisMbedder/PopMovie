package com.example.luis.popmovie.utils;

import com.example.luis.popmovie.adapters.GridViewAdapter;

/**
 * Created by Luis on 3/23/2016.
 */
public interface GridClickListener {
    /**
     * Called when a grid item is clicked
     * @param holder The ViewHolder for the clicked item
     * @param position The position in the grid of the item that was clicked
     */
    void onGridClicked(GridViewAdapter.movieHolder holder, int position);
}
