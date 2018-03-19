package com.oc.bashalir.moodtracker.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.oc.bashalir.moodtracker.R;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Created by Sumer on 08/03/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private final Mood mood1= new Mood(R.drawable.smiley_sad,R.color.faded_red,"Sad",R.raw.sad);
    private final Mood mood2= new Mood(R.drawable.smiley_disappointed,R.color.warm_grey,"Disappointed",R.raw.disappointed);
    private final Mood mood3= new Mood(R.drawable.smiley_normal,R.color.cornflower_blue_65, "Normal",R.raw.normal);
    private final Mood mood4= new Mood(R.drawable.smiley_happy,R.color.light_sage,"Happy",R.raw.happy);
    private final Mood mood5= new Mood(R.drawable.smiley_super_happy,R.color.banana_yellow,"Super Happy",R.raw.superhappy);

    ArrayList<Mood> mList= new ArrayList<>(Arrays.asList(mood1, mood2, mood3, mood4, mood5));

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell,parent,false);




            return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {

       Mood mood = mList.get(position);
       holder.display(mood);

    }

    public class MoodViewHolder  extends RecyclerView.ViewHolder{

        private final CardView backMood;
        private final ImageView imgMood;
        private Mood currentMood;
        private MediaPlayer mPlayer = null;

     public MoodViewHolder(final View itemView) {

         super (itemView);

          imgMood=(itemView.findViewById(R.id.list_cell_image_mood_img));
          backMood = (itemView.findViewById(R.id.list_cell_back_mood_cv));

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Toast.makeText(view.getContext(), currentMood.getDescription(), Toast.LENGTH_SHORT).show();

                      mPlayer = MediaPlayer.create(view.getContext(), currentMood.getSound());
                      mPlayer.start();


              }
          });
     }


     private void display (Mood mood) {

         currentMood = mood;
       Log.d("Debug","Color :"+mood.getBackColor());
       imgMood.setImageResource(mood.getSmiley());
       backMood.setBackgroundResource(mood.getBackColor());
     }
    }





}
