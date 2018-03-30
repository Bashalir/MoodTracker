package com.oc.bashalir.moodtracker.view;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.Mood;

/**
 * Created by Sumer on 20/03/2018.
 */

public class MoodViewHolder extends RecyclerView.ViewHolder{


        private final CardView backMood;
        private final ImageView imgMood;
        private Mood currentMood;


        public MoodViewHolder(final View itemView) {

            super (itemView);

            imgMood=(itemView.findViewById(R.id.list_cell_image_mood_img));
            backMood = (itemView.findViewById(R.id.list_cell_back_mood_cv));

        }

        public void display (Mood mood) {

            currentMood = mood;

            Log.d("Debug","Color :"+mood.getBackColor()+" "+R.color.banana_yellow);
            imgMood.setImageDrawable(mood.getSmiley());
            backMood.setBackgroundResource(mood.getBackColor());

        }

}
