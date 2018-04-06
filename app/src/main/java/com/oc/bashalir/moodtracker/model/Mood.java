package com.oc.bashalir.moodtracker.model;

import android.graphics.drawable.Drawable;


/**
 * Gathers all the information of the mood
 */
public class Mood {

    private Drawable mSmiley;
    private Integer mBackColor;
    private String mDescription;
    private Integer mSound;

    /**
     * Constructor of Mood class
     *
     * @param smiley      : Smiley image link
     * @param backColor   : Color code of the bottom of the cardview
     * @param description : Description of the posted smiley
     * @param sound       : Mood sound link
     */

    public Mood(Drawable smiley, Integer backColor, String description, Integer sound) {
        this.setSmiley(smiley);
        this.setBackColor(backColor);
        this.setDescription(description);
        this.setSound(sound);
    }

    /**
     * Getters and Setters
     */

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
