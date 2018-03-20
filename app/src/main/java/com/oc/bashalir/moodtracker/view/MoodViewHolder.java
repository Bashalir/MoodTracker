package com.oc.bashalir.moodtracker.view;

import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.Mood;

/**
 * Created by Sumer on 20/03/2018.
 */

public class MoodViewHolder extends RecyclerView.ViewHolder{


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

                    // Display a message of the selected Mood
                    Toast.makeText(view.getContext(), currentMood.getDescription(), Toast.LENGTH_SHORT).show();

                    // Play the Sound of the selected Mood
                    mPlayer = MediaPlayer.create(view.getContext(), currentMood.getSound());
                    mPlayer.start();

                    Log.d("Debug","Sound :"+currentMood.getDescription());
                    Log.d("Debug","Sound :"+currentMood.getSound());

                }
            });
        }


        public void display (Mood mood) {

            currentMood = mood;

            Log.d("Debug","Color :"+mood.getBackColor());
            imgMood.setImageResource(mood.getSmiley());
            backMood.setBackgroundResource(mood.getBackColor());

        }


}
