package com.oc.bashalir.moodtracker.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.view.MoodViewHolder;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Sumer on 08/03/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    // THE MOOD LIST DATA
    private final Mood mood1= new Mood(R.drawable.smiley_sad,R.color.faded_red,"Sad",R.raw.sad);
    private final Mood mood2= new Mood(R.drawable.smiley_disappointed,R.color.warm_grey,"Disappointed",R.raw.disappointed);
    private final Mood mood3= new Mood(R.drawable.smiley_normal,R.color.cornflower_blue_65, "Normal",R.raw.normal);
    private final Mood mood4= new Mood(R.drawable.smiley_happy,R.color.light_sage,"Happy",R.raw.happy);
    private final Mood mood5= new Mood(R.drawable.smiley_super_happy,R.color.banana_yellow,"Super Happy",R.raw.superhappy);

    ArrayList<Mood> mList= new ArrayList<>(Arrays.asList(mood1, mood2, mood3, mood4, mood5));


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


}
