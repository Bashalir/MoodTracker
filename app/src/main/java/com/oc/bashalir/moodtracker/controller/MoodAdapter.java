package com.oc.bashalir.moodtracker.controller;

import android.graphics.Color;
import android.support.v7.widget.CardView;
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


    private final List<Pair<Integer,String>> listMood = Arrays.asList (
            Pair.create(R.drawable.smiley_sad, "#FFDE3C50"),
            Pair.create(R.drawable.smiley_disappointed,  "#ff9b9b9b"),
            Pair.create(R.drawable.smiley_normal, "#a5468ad9"),
            Pair.create(R.drawable.smiley_happy,  "#ffb8e986"),
            Pair.create(R.drawable.smiley_super_happy, "#fff9ec4f")
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

        Pair<Integer,String> pair = listMood.get(position);
        holder.display(pair);
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder{

        private final CardView backMood;
        private final ImageView imgMood;

     public MoodViewHolder(final View itemView) {
          super (itemView);
          imgMood=(itemView.findViewById(R.id.list_cell_image_mood_img));
          backMood = (itemView.findViewById(R.id.list_cell_back_mood_cv));
     }

     public void display (Pair<Integer, String> pair) {


       Log.d("Debug","Color :"+pair.second);

        imgMood.setImageResource(pair.first);
      //  backMood.setCardBackgroundColor(Color.parseColor("#ffde3c50"));
         backMood.setCardBackgroundColor(Color.parseColor(pair.second));

     }
    };






}
