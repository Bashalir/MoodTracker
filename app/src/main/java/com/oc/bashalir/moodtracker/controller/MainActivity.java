package com.oc.bashalir.moodtracker.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.Mood;
import com.oc.bashalir.moodtracker.model.MoodAdapter;
import com.oc.bashalir.moodtracker.model.MoodDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Configure recyclerview, manage moods
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String LIST_MOOD = "LIST_MOOD";
    private static TypedArray tSmiley;
    private static TypedArray tColor;
    private static String[] tDescription;
    private static TypedArray tSound;
    private RecyclerView mListMoodRecyclerView;
    private ImageView mCommentButton;
    private ImageView mHistoryButton;
    private SharedPreferences mPreferences;
    private int mMoodPosition = 2;
    private MoodAdapter mAdapter;
    private MediaPlayer mPlayer = null;
    private Context mContext;
    private List<MoodDay> mMoodDayList = null;
    private String mComment = null;
    private Gson gson;
    private String json;
    private Date mRightNow;
    private int mPosition;

    /**
     * at startup Create a mood list and configure RecyclerView
     *
     * @param : savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListMoodRecyclerView = findViewById(R.id.activity_main_listMood_rv);
        mCommentButton = findViewById(R.id.activity_main_comment_btn);
        mHistoryButton = findViewById(R.id.activity_main_history_btn);

        mPreferences = getPreferences(MODE_PRIVATE);
        gson = new Gson();

        //Create and load a mood list
        List<MoodDay> mMoodDayList = new ArrayList<>();
        mMoodDayList = this.loadMoodDayList();

        // Configure RecyclerView
        this.configureRecyclerView();
        this.configureOnClickRecyclerView();

        // the user clicks the comment button
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addComment();
            }
        });

        // the user clicks the History button
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                historyActivityIntent.putExtra("JSON", json);
                startActivity(historyActivityIntent);
            }
        });

    }

    /**
     * Configuration of the RecyclerView
     */
    private void configureRecyclerView() {

        Resources res = getResources();

        tSmiley = res.obtainTypedArray(R.array.smiley);
        tColor = res.obtainTypedArray(R.array.color);
        tDescription = res.getStringArray(R.array.description);
        tSound = res.obtainTypedArray(R.array.sound);

        //Declare and load the table for all Mood
        Mood[] tMood = new Mood[5];

        for (int i = 0; i <= 4; i++) {
            tMood[i] = new Mood(tSmiley.getDrawable(i), tColor.getResourceId(i, i), tDescription[i], tSound.getResourceId(i, i));
            Log.d(TAG, tSmiley.getDrawable(i) + " " + tColor.getResourceId(i, i) + " " + tDescription[i] + " " + tSound.getResourceId(i, i));
        }

        ArrayList<Mood> mList = new ArrayList<>(Arrays.asList(tMood[0], tMood[1], tMood[2], tMood[3], tMood[4]));


        mAdapter = new MoodAdapter(mList);
        mListMoodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListMoodRecyclerView.setAdapter(mAdapter);

        //Position and adjust the scroll
        mListMoodRecyclerView.scrollToPosition(mMoodPosition);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mListMoodRecyclerView);

    }

    /**
     * Play a sound and Display the mood name when the user click
     */
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mListMoodRecyclerView, R.layout.list_cell)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        mPosition = position;

                        Log.d(TAG, "Position :" + position);

                        mContext = getApplicationContext();
                        Mood mood = mAdapter.getMood(position);


                        // Display a message of the selected Mood
                        Toast.makeText(mContext, mood.getDescription(), Toast.LENGTH_SHORT).show();

                        // Play the Sound of the selected Mood
                        mPlayer = MediaPlayer.create(mContext, mood.getSound());
                        mPlayer.start();

                        Log.d(TAG, "Description :" + mood.getDescription());
                        Log.d(TAG, "Sound :" + mood.getSound());

                        addMoodDayList(position, mComment);
                        mHistoryButton.setVisibility(View.VISIBLE);

                    }
                });
    }

    /**
     * Record daily moods with his comments
     *
     * @param position : the position that assigns the selected mood.
     */
    private void addMoodDayList(int position, String comment) {

        mRightNow = new Date();

        int mPositionMoodDayList = 0;
        boolean searchDay = false;

        // check if the mood list is not empty
        if (!(mMoodDayList == null)) {
            for (int i = 0; i < mMoodDayList.size(); i++) {

                // check if a mood of the day has already been recorded
                if (formatDate(mMoodDayList.get(i).getDay()).contains(formatDate(mRightNow))) {
                    mPositionMoodDayList = i;
                    searchDay = true;
                    Log.d(TAG, "Mood : TRUE");
                }
            }
        }

        Log.d(TAG, "Date :" + mRightNow);

        //Add the mood of the day in the list
        MoodDay moodSelect = new MoodDay(position, comment, mRightNow);
        Log.d(TAG, "Mood :" + moodSelect.getPosition() + " " + moodSelect.getDay() + " " + moodSelect.getComment());

        //Add the mood of the day or change if already exists
        if (!searchDay) {
            mMoodDayList.add(moodSelect);
        } else {
            Log.d(TAG, "Position :" + mPositionMoodDayList);
            Log.d(TAG, "Size :" + mMoodDayList);
            mMoodDayList.set(mPositionMoodDayList, moodSelect);
        }

        saveMoodDayList(mMoodDayList);

        //Log of all Mood
        for (MoodDay m : mMoodDayList) {
            Log.d(TAG, "ListMood :" + m.getPosition() + " " + m.getDay() + " " + m.getComment());
        }

    }

    /**
     * Load of the SharedPreferences the mood list save
     *
     * @return get the list of moods or a new
     */
    public List<MoodDay> loadMoodDayList() {

        json = mPreferences.getString(LIST_MOOD, null);

        //check if there is already a list
        if (json != null) {

            mMoodDayList = gson.fromJson(json, new TypeToken<ArrayList<MoodDay>>() {
            }.getType());

            MoodDay lastMoodDay = mMoodDayList.get(mMoodDayList.size() - 1);

            //Assign the scroll position according to the last entry
            mMoodPosition = lastMoodDay.getPosition();
            mPosition = mMoodPosition;

            mHistoryButton.setVisibility(View.VISIBLE);

            //Log of all element of the list
            for (MoodDay m : mMoodDayList) {
                Log.d(TAG, "ListMood :" + m.getPosition() + " " + m.getDay() + " " + m.getComment());
            }

            return mMoodDayList;
        } else {
            mHistoryButton.setVisibility(View.INVISIBLE);

        }
        return mMoodDayList = new ArrayList<>();
    }

    /**
     * Save the entire mood list
     *
     * @param moodDayList : the list of all the moods already added
     */
    private void saveMoodDayList(List<MoodDay> moodDayList) {

        //Sort the list
        Collections.sort(moodDayList, new Comparator<MoodDay>() {
            @Override
            public int compare(MoodDay o1, MoodDay o2) {

                return o1.getDay().compareTo(o2.getDay());
            }
        });

        json = gson.toJson(moodDayList);
        //save in SharedPreferences
        mPreferences.edit()
                .putString(LIST_MOOD, json)
                .apply();
    }

    /**
     * Open an AlertDialog for save user comment
     */
    private void addComment() {

        final EditText input = new EditText(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View mViewDialog = inflater.inflate(R.layout.comment_dialog, null, false);

        input.setInputType(InputType.TYPE_CLASS_TEXT);

        //Creation of a dialog box with the positive and negative choice
        AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle(R.string.alertDialogTitle)

                .setView(mViewDialog)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    //When the user click positive
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText commentEditText = ((AlertDialog) dialog).findViewById(R.id.comment_dialog_comment_etx);
                        mComment = commentEditText.getText().toString();

                        //makes comments null if there are just one or more spaces
                        if (mComment.replaceAll(" ", "").equals("")) {
                            mComment = null;
                        }

                        addMoodDayList(mPosition, mComment);

                        Log.d("DEBUG", "Comment :" + mComment);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    //When the user click Negative
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    /**
     * Format a date into a string
     *
     * @param date : Today's date
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