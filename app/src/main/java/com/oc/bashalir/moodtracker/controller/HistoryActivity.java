package com.oc.bashalir.moodtracker.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.HistoryAdapter;
import com.oc.bashalir.moodtracker.model.MoodDay;

import java.util.ArrayList;
import java.util.List;


/**
 * Show moods previously save
 */
public class HistoryActivity extends AppCompatActivity {


    public static final String TAG = "HistoryActivity";
    private RecyclerView mHistoryRecyclerView;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mHistoryRecyclerView = findViewById(R.id.activity_history_listMood_rv);

        //load the MoodDayList
        String json = getIntent().getExtras().getString("JSON");
        List<MoodDay> moodList = loadMoodDayList(json);

        //check if the list exists
        if (json != null) {
            Log.d("JSON", json);
        }
        int[] tWindowsSize = getSizeWindows();

        Context mContext = getApplicationContext();
        //configuration of the Adapter for RecyclerView
        mHistoryAdapter = new HistoryAdapter(moodList, tWindowsSize[0], tWindowsSize[1], mContext);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);

        //Position and adjust the scroll
        mHistoryRecyclerView.scrollToPosition(moodList.size() - 1);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mHistoryRecyclerView);

    }

    /**
     * Load the MoodDayList from a json String
     *
     * @param json the string json containing the list
     * @return the mood list save
     */
    public List<MoodDay> loadMoodDayList(String json) {

        List<MoodDay> mMoodDayList = new ArrayList<>();

        Gson gson;
        gson = new Gson();

        // load the list if the string json is not empty
        if (json != null)
            mMoodDayList = gson.fromJson(json, new TypeToken<ArrayList<MoodDay>>() {
            }.getType());

        //  display the log of all elements on the list
        for (MoodDay m : mMoodDayList) {
            Log.d(TAG, "ListMood :" + m.getPosition() + " " + m.getDay() + " " + m.getComment());
        }

        return mMoodDayList;
    }

    /**
     * Give the width and the length of the screen in pixel without the status bar
     *
     * @return Int Table tWindowsSize with [0]windowsHeight, [1]windowsWidth
     */
    public int[] getSizeWindows() {
        DisplayMetrics metrics = new DisplayMetrics();

        //get the real screen size
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        int realWidth = metrics.widthPixels;
        //get the usual screen size
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int useWidth = metrics.widthPixels;
        int useHeight = metrics.heightPixels;

        int statusBarHeight = 0;
        int windowsWidth = useWidth;

        //Set the screen size according to a portrait or landscape view
        if (useHeight > useWidth) {
            statusBarHeight = (realHeight - useHeight) / 2;
        } else {
            statusBarHeight = (realWidth - useWidth) / 2;
        }
        int windowsHeight = useHeight - statusBarHeight;

        //load the table
        int tWindowsSize[] = {windowsHeight, windowsWidth};

        Log.d(TAG, "Screen Dimension = USE : " + useHeight + "x" + useWidth + " REAL : " + realHeight + "x" + realWidth + " BAR : " + statusBarHeight);

        return tWindowsSize;
    }

}
