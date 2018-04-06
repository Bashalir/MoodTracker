package com.oc.bashalir.moodtracker.model;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.view.MoodViewHolder;

import java.util.ArrayList;


/**
 * Adapter from the recyclerview of the mainActivity
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    private ArrayList<Mood> mList;

    /**
     * Constructor of MoodAdapter
     *
     * @param list : list of Mood
     */
    public MoodAdapter(ArrayList<Mood> list) {
        mList = list;
    }


    /**
     * Get the size of items in adapter
     *
     * @return the size of items in adapter
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * Create View Holder and inflating
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);

        return new MoodViewHolder(view);
    }

    /**
     * Update View Holder with Mood List
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {

        Mood mood = mList.get(position);
        holder.display(mood);

    }

    /**
     * @param position : the position of the mood select for the day
     * @return position of the mood in the List
     */
    public Mood getMood(int position) {
        return mList.get(position);
    }


}
