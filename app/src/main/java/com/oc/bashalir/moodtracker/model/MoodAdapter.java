package com.oc.bashalir.moodtracker.model;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.view.MoodViewHolder;

import java.util.ArrayList;


/**
 * Created by Sumer on 08/03/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    private ArrayList<Mood> mList;

    public MoodAdapter(ArrayList<Mood> list) {
        mList = list;
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return mList.size();
    }

    // CREATE VIEW HOLDER AND INFLATING
    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell,parent,false);

        return new MoodViewHolder(view);
    }


    // UPDATE VIEW HOLDER WITH MOOD LIST
    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {

       Mood mood = mList.get(position);
       holder.display(mood);

    }

    public Mood getMood(int position){
        return  mList.get(position);
    }


}
