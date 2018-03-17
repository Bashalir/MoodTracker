package com.oc.bashalir.moodtracker.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oc.bashalir.moodtracker.R;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Created by Sumer on 08/03/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private final Mood mood1= new Mood(R.drawable.smiley_sad,R.color.faded_red,"Sad",R.drawable.smiley_sad);
    private final Mood mood2= new Mood(R.drawable.smiley_disappointed,R.color.warm_grey,"Disappointed",R.drawable.smiley_sad);
    private final Mood mood3= new Mood(R.drawable.smiley_normal,R.color.cornflower_blue_65, "Normal",R.drawable.smiley_sad);
    private final Mood mood4= new Mood(R.drawable.smiley_happy,R.color.light_sage,"Happy",R.drawable.smiley_sad);
    private final Mood mood5= new Mood(R.drawable.smiley_super_happy,R.color.banana_yellow,"Super Happy",R.drawable.smiley_sad);

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

    public class MoodViewHolder extends RecyclerView.ViewHolder{

        private final CardView backMood;
        private final ImageView imgMood;

     public MoodViewHolder(final View itemView) {
          super (itemView);
          imgMood=(itemView.findViewById(R.id.list_cell_image_mood_img));
          backMood = (itemView.findViewById(R.id.list_cell_back_mood_cv));
     }

     private void display (Mood mood) {


       Log.d("Debug","Color :"+mood.getBackColor());

       imgMood.setImageResource(mood.getSmiley());
      // backMood.setCardBackgroundColor(Color.parseColor(mood.getBackColor()));
       //  backMood.setCardBackgroundColor(R.drawable.smiley_sad);
         backMood.setBackgroundResource(mood.getBackColor());
     }
    }





}