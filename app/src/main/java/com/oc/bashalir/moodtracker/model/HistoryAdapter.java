package com.oc.bashalir.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.bashalir.moodtracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Bashalir on 10/03/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private SharedPreferences mPreferences;
    protected List<MoodDay> mMoodDayList = new ArrayList<>();
    public static final String TAG = "HistoryActivity";
    private static TypedArray tColor;
    private int mHeight;
    private int mWidth;
    private Context mContext;
    private long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;

    public HistoryAdapter(List<MoodDay> listMoodDay, TypedArray Color, int x, int y) {

        mHeight = x;
        mWidth = y;
        mMoodDayList = listMoodDay;
        tColor = Color;
    }

    @Override
    public int getItemCount() {

        return mMoodDayList.size();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_bar, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {

        MoodDay moodDay = mMoodDayList.get(position);
        holder.display(moodDay);

    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private final CardView barHistory;
        private final ImageView commentHistory;
        private final TextView dateHistory;
        private String mComment;

        public HistoryViewHolder(final View itemView) {
            super(itemView);
            barHistory = (itemView.findViewById(R.id.list_bar_bar_cv));
            commentHistory = (itemView.findViewById(R.id.list_bar_comment_btn));
            dateHistory = (itemView.findViewById(R.id.list_bar_date_txt));
        }

        /**
         * Display a mood bar
         *
         * @param moodDay The mood of the day
         */
        private void display(MoodDay moodDay) {
            commentHistory.setVisibility(View.INVISIBLE);
            mComment = moodDay.getComment();

            //Change the color according to the mood
            int position = moodDay.getPosition();
            barHistory.setBackgroundResource(tColor.getResourceId(position, position));

            //Show comment button if there is a comment
            if (mComment != null) {
                //Do not display the comment button if there is only just one or more spaces
                if (mComment.replaceAll(" ", "").equals("")) {
                    mComment = null;
                }
                commentHistory.setVisibility(View.VISIBLE);
            }

            Date date = moodDay.getDay();
            Date rightNow = new Date();
            //dateHistory.setText(formatDate(date));

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 1);
            date=cal.getTime();
            cal.setTime(rightNow);
            cal.set(Calendar.HOUR_OF_DAY, 24);
            rightNow=cal.getTime();

            int numberOfDay=0;
            int diff = (int)Math.abs(rightNow.getTime() - date.getTime());
            long diffDay =  diff /  CONST_DURATION_OF_DAY;
            if (date.getTime()>rightNow.getTime()){numberOfDay=99;} else {numberOfDay =(int) diffDay;}

            String displayDate;

            switch (numberOfDay) {
                case 7:
                    displayDate = "Il y'as une semaine";
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    displayDate = "il y'as " + numberOfDay + " jours";
                    break;
                case 2:
                    displayDate = "Avant hier";
                    break;
                case 1:
                    displayDate = "Hier";
                    break;
                case 0:
                    displayDate = "Aujourd'hui";
                    break;
                default:
                    displayDate = formatDate(date);
                    break;
            }

            Log.d(TAG,"date : "+displayDate+diffDay+" "+diff);


            dateHistory.setText(displayDate);

            Log.d(TAG, "Date Comparaison : " + diffDay + " " + numberOfDay);

            //Calculation and defined the size of the mood bar
            ViewGroup.LayoutParams params = barHistory.getLayoutParams();
            params.height = mHeight / 7;
            params.width = (mWidth / 5) * (position + 1);
            barHistory.setLayoutParams(params);

            /**
             * Shows the comment in a toast at the click of the user
             */
            commentHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Insert the comment in the toast
                    Toast toast = Toast.makeText(commentHistory.getContext(), mComment, Toast.LENGTH_SHORT);
                    View view = toast.getView();

                    // Change of background colors and toast text
                    TextView text = view.findViewById(android.R.id.message);
                    text.setTextColor(Color.WHITE);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(Color.DKGRAY);

                    //Display the toast
                    toast.show();
                    Log.d(TAG, "Comment :" + mComment);
                }
            });
        }
    }

    /**
     * Format a date into a string
     *
     * @param date Today's date
     * @return the date of the day in string
     */
    public String formatDate(Date date) {

        //Get format date
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd/MM/yy");

        String dateResult = formater.format(date);
        return dateResult;
    }




}
