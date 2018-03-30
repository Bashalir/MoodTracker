package com.oc.bashalir.moodtracker.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Sumer on 11/03/2018.
 */

public class Mood {

    private Drawable mSmiley; // Smiley image link
    private Integer mBackColor; // Color code of the bottom of the cardview
    private String mDescription; // Description of the posted smiley
    private Integer mSound; //  Mood sound link

    public Mood(Drawable smiley, Integer backColor, String description, Integer sound) {
        this.setSmiley(smiley);
        this.setBackColor(backColor);
        this.setDescription(description);
        this.setSound(sound);
    }

    public Drawable getSmiley() {
        return mSmiley;
    }

    public void setSmiley(Drawable smiley) {
        mSmiley = smiley;
    }

    public Integer getBackColor() {
        return mBackColor;
    }

    public void setBackColor(Integer backColor) {
        mBackColor = backColor;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Integer getSound() {
        return mSound;
    }

    public void setSound(Integer sound) {
        mSound = sound;
    }
}
