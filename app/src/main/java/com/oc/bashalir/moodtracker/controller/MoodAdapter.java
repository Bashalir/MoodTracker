package com.oc.bashalir.moodtracker.controller;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.oc.bashalir.moodtracker.R;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Sumer on 08/03/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {


    private final List<Pair<Integer,Integer>> listMood = Arrays.asList (
            Pair.create(R.drawable.smiley_sad, R.color.faded_red),
            Pair.create(R.drawable.smiley_disappointed,  R.color.warm_grey),
            Pair.create(R.drawable.smiley_normal, R.color.light_sage),
            Pair.create(R.drawable.smiley_happy,  R.color.cornflower_blue_65),
            Pair.create(R.drawable.smiley_super_happy, R.color.banana_yellow)
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

        Pair<Integer,Integer> pair = listMood.get(position);
        holder.display(pair);
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder{

        private final RelativeLayout backMood;
        private final ImageView imgMood;

     public MoodViewHolder(final View itemView) {
          super (itemView);

         imgMood=(itemView.findViewById(R.id.list_cell_image_mood_img));
          backMood = (itemView.findViewById(R.id.list_cell_back_mood_rl));

     }

     public void display (Pair<Integer, Integer> pair) {


       ColorDrawable cd=new ColorDrawable(pair.second);

       Log.d("Debug","Color :"+pair.second);

        imgMood.setImageResource(pair.first);
        backMood.setBackground(cd);

     }
    };






}
