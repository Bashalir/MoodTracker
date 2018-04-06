package com.oc.bashalir.moodtracker.model;

import java.util.Date;


/**
 * Gathers all the information of the moodDay
 */
public class MoodDay {

    private Integer mPosition;
    private String mComment;
    private Date mDay;

    /**
     * Constructor of MoodDay class
     *
     * @param position : the position of the mood select for the day
     * @param comment  : comment of the day
     * @param date     : Day of the Mood
     */
    public MoodDay(Integer position, String comment, Date date) {
        this.setPosition(position);
        this.setComment(comment);
        this.setDay(date);
    }


    /**
     * Getters and Setters
     */

    public Integer getPosition() {
        return mPosition;
    }

    public void setPosition(Integer position) {
        mPosition = position;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public Date getDay() {
        return mDay;
    }

    public void setDay(Date day) {
        mDay = day;
    }


}
