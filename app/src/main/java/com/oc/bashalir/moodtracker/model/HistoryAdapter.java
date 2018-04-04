package com.oc.bashalir.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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


    public static final String TAG = "HistoryActivity";
    private static TypedArray tColor;
    protected List<MoodDay> mMoodDayList = new ArrayList<>();
    private SharedPreferences mPreferences;
    private int mHeight;
    private int mWidth;
    private Context mContext;
    private Resources mResources;

    /**
     * Configure history adapter by loading it with default settings
     *
     * @param listMoodDay the mood list of the day
     * @param context     the context of the HistoryActivity
     * @param x           the pixel height of the window
     * @param y           the pixel width of the window
     */
    public HistoryAdapter(List<MoodDay> listMoodDay, int x, int y, Context context) {

        mContext = context;
        mResources = mContext.getResources();
        mHeight = x;
        mWidth = y;
        mMoodDayList = listMoodDay;
        tColor = mResources.obtainTypedArray(R.array.color);

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

    /**
     * gives the number of days from a date to now
     *
     * @param date the date of the mood bar
     * @return the number of days from a date to now
     */
    private int numberOfDay(Date date) {

        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24; // number of second for one day
        Date rightNow = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        date = cal.getTime();
        cal.setTime(rightNow);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        rightNow = cal.getTime();

        int numberOfDay = 0;
        int diff = (int) Math.abs(rightNow.getTime() - date.getTime());
        long diffDay = diff / CONST_DURATION_OF_DAY;

        //checked and attribute the number of days
        if (date.getTime() > rightNow.getTime()) {
            numberOfDay = 99;
        } else {
            numberOfDay = (int) diffDay;
        }

        Log.d(TAG, "Date Comparaison : " + diffDay + " " + numberOfDay);

        return numberOfDay;
    }

    /**
     * gives the text to display the date in the mood bar
     *
     * @param numberOfDay the number of days from a date to now
     * @param date        the date of the mood bar
     * @return the text to display the date
     */
    private String displayDate(int numberOfDay, Date date) {

        String displayDate = "";

        //Give a text to the number of days
        switch (numberOfDay) {
            case 7:
                displayDate = mResources.getString(R.string.aWeekAgo);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                displayDate = String.format(mResources.getString(R.string.numberDay), numberOfDay);
                break;
            case 2:
                displayDate = mResources.getString(R.string.twoDayAgo);
                break;
            case 1:
                displayDate = mResources.getString(R.string.yesterday);
                break;
            case 0:
                displayDate = mResources.getString(R.string.today);
                break;
            default:
                displayDate = formatDate(date);
                break;
        }

        return displayDate;
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

            //Display the date of the mood bar
            Date date = moodDay.getDay();
            int numberOfDay = numberOfDay(date);
            String displayDate = displayDate(numberOfDay, date);
            dateHistory.setText(displayDate);

            //Calculation and defined the size of the mood bar
            ViewGroup.LayoutParams params = barHistory.getLayoutParams();
            params.height = mHeight / 7;
            params.width = (mWidth / 5) * (position + 1);
            barHistory.setLayoutParams(params);

            //Shows the comment in a toast at the click of the user
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
}
