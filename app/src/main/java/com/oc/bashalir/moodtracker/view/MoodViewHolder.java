package com.oc.bashalir.moodtracker.view;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.Mood;

/**
 * Class MoodViewHolder
 */
public class MoodViewHolder extends RecyclerView.ViewHolder {


    private final CardView backMood;
    private final ImageView imgMood;
    private Mood currentMood;

    /**
     * Constructor of the View Holder
     *
     * @param itemView
     */
    public MoodViewHolder(final View itemView) {

        super(itemView);

        imgMood = (itemView.findViewById(R.id.list_cell_image_mood_img));
        backMood = (itemView.findViewById(R.id.list_cell_back_mood_cv));

    }

    /**
     * Set the mood smiley and its background color
     *
     * @param mood the mood selected
     */
    public void display(Mood mood) {

        currentMood = mood;

        Log.d("Debug", "Color :" + mood.getBackColor());

        imgMood.setImageDrawable(mood.getSmiley());
        backMood.setBackgroundResource(mood.getBackColor());

    }

}
