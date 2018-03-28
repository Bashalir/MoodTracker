package com.oc.bashalir.moodtracker.model;
import java.util.Date;

/**
 * Created by Sumer on 13/03/2018.
 */

public class MoodDay  {

    private Integer mPosition; // the position of the mood select for the day
    private String mComment; // comment of the day
    private String mDay; // Day of the Mood

    public MoodDay(Integer position, String comment, String date) {
        this.setPosition(position);
        this.setComment(comment);
        this.setDate(date);
    }

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

    public String getDay() {
        return mDay;
    }

    public void setDate(String day) {
        mDay = day;
    }



}
