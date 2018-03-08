package com.oc.bashalir.moodtracker.controller;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oc.bashalir.moodtracker.R;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Sumer on 08/03/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {


    private final List<Pair<String,Integer>> listMood = Arrays.asList (
            Pair.create("sad",(int) R.color.faded_red),
            Pair.create("disappointed", (int) R.color.warm_grey),
            Pair.create("normal", (int) R.color.light_sage),
            Pair.create("happy.png", (int) R.color.cornflower_blue_65),
            Pair.create("super-happy.png",(int) R.color.banana_yellow)
            );


    @Override
    public int getItemCount() {
        return listMood.size();
    }

    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell,parent,false);

            return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {

        Pair<String,Integer> pair = listMood.get(position);
        holder.display(pair);
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder{

        private final RelativeLayout backMood;
        private Pair<String,Integer> currentPair;

     public MoodViewHolder(final View itemView) {
          super (itemView);

          backMood = (itemView.findViewById(R.id.list_cell_back_mood_rl));

     }

     public void display (Pair<String, Integer> pair) {
         currentPair=pair;
       //  backMood.setBackground((int) pair.second);

     }
    };






}
