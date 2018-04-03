package com.oc.bashalir.moodtracker.controller;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.HistoryAdapter;
import com.oc.bashalir.moodtracker.model.MoodDay;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.oc.bashalir.moodtracker.controller.MainActivity.tColor;


public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mHistoryRecyclerView;
    public static final String TAG = "HistoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mHistoryRecyclerView = findViewById(R.id.activity_history_listMood_rv);

        String json = getIntent().getExtras().getString("JSON");
        List<MoodDay> moodList = loadMoodDayList(json);
        if (json != null) {


            Log.d("JSON", json);

        } else {
            // Display a message of no registred
            Toast.makeText(getApplicationContext(), "Aucun Enregistrement", Toast.LENGTH_SHORT).show();
        }

        int [] tWindowsSize=getSizeWindows();

        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(new HistoryAdapter(moodList, tColor, tWindowsSize[0],tWindowsSize[1]));

        mHistoryRecyclerView.scrollToPosition(moodList.size() - 1);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mHistoryRecyclerView);

    }


    public List<MoodDay> loadMoodDayList(String json) {

        List<MoodDay> mMoodDayList = new ArrayList<>();

        Gson gson;
        gson = new Gson();

        if (json != null)
            mMoodDayList = gson.fromJson(json, new TypeToken<ArrayList<MoodDay>>() {
            }.getType());

        for (MoodDay m : mMoodDayList) {
            Log.d(TAG, "ListMood :" + m.getPosition() + " " + m.getDay() + " " + m.getComment());
        }

        return mMoodDayList;
    }

    public int[] getSizeWindows() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        int realWidth = metrics.widthPixels;
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int useWidth = metrics.widthPixels;
        int useHeight = metrics.heightPixels;
        int statusBarHeight = 0;
        int windowsWidth = useWidth;

        if (useHeight > useWidth) {
            statusBarHeight = (realHeight - useHeight) / 2;
        } else {
            statusBarHeight = (realWidth - useWidth) / 2;
        }

        int windowsHeight = useHeight - statusBarHeight;

        int tWindowsSize[]={windowsHeight,windowsWidth};

        Log.d(TAG, "Screen Dimension = USE : " + useHeight + "x" + useWidth + " REAL : " + realHeight + "x" + realWidth + " BAR : " + statusBarHeight);


        return tWindowsSize;
    }

}
