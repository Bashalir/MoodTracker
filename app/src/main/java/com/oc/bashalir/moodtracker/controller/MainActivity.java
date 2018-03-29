package com.oc.bashalir.moodtracker.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mListMoodRecyclerView;
    private ImageView mCommentButton;
    private ImageView mHistoryButton;
    private SharedPreferences mPreferences;
    private int mMoodPosition = 2;
    private MoodAdapter mAdapter;
    private MediaPlayer mPlayer = null;
    private Context mContext;
    protected List<MoodDay> mMoodDayList=null;
    private String mComment = null;
    private Gson gson;

    public static final String TAG = "MainActivity";
    public static final String LIST_MOOD = "LIST_MOOD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListMoodRecyclerView = findViewById(R.id.activity_main_listMood_rv);
        mCommentButton = findViewById(R.id.activity_main_comment_btn);
        mHistoryButton = findViewById(R.id.activity_main_history_btn);

        mPreferences = getPreferences(MODE_PRIVATE);
        gson= new Gson();

        List<MoodDay>mMoodDayList=new ArrayList<>();

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();
        mMoodDayList=this.loadMoodDayList();

        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComment();
            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent=new Intent(MainActivity.this, HistoryActivity.class);
               // historyActivityIntent.putExtra();
                startActivity(historyActivityIntent);
            }
        });

    }

    private void configureRecyclerView() {

        mAdapter = new MoodAdapter();
        mListMoodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListMoodRecyclerView.setAdapter(mAdapter);

        mListMoodRecyclerView.scrollToPosition(mMoodPosition);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mListMoodRecyclerView);

    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mListMoodRecyclerView, R.layout.list_cell)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {



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

                        addMoodDayList(position);

                    }
                });
    }

        private void addMoodDayList(int position) {

        int mPositionMoodDayList=0;
        boolean searchDay=false;


        if (!(mMoodDayList==null)){
        for (int i = 0; i < mMoodDayList.size(); i++) {
            if (mMoodDayList.get(i).getDay().contains(getDay())) {
                mPositionMoodDayList = i;
                searchDay=true;
                Log.d(TAG, "Mood : TRUE");
            }
        }}

        Log.d(TAG, "Date :" + getDay());

        //Add the mood of the day in the list
        MoodDay moodSelect = new MoodDay(position, mComment, getDay());
        Log.d(TAG,"Mood :"+moodSelect.getPosition()+" "+moodSelect.getDay()+" "+moodSelect.getComment());

        if (!searchDay) {
            mMoodDayList.add(moodSelect);
        }
        else {
            Log.d(TAG, "Position :" + mPositionMoodDayList);
            Log.d(TAG, "Size :" + mMoodDayList);
            mMoodDayList.set(mPositionMoodDayList,moodSelect);
        }

        saveMoodDayList( mMoodDayList);

      for (MoodDay m:mMoodDayList)
       { Log.d(TAG,"ListMood :"+m.getPosition()+" "+m.getDay()+" "+m.getComment());

       }


    }

    public List<MoodDay> loadMoodDayList(){

        String json = mPreferences.getString(LIST_MOOD,null);

        if (json !=null)
            mMoodDayList=gson.fromJson(json, new TypeToken<ArrayList<MoodDay>>() {}.getType());


        for (MoodDay m:mMoodDayList)
        { Log.d(TAG,"ListMood :"+m.getPosition()+" "+m.getDay()+" "+m.getComment());
        }

        return mMoodDayList;
    }

    private void saveMoodDayList(List<MoodDay> moodDayList){

        mPreferences.edit()
                .putString(LIST_MOOD,gson.toJson(moodDayList))
                .apply();

    }


    private void addComment() {

        final EditText input = new EditText(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View mviewdialog = inflater.inflate(R.layout.comment_dialog, null, false);

        input.setInputType(InputType.TYPE_CLASS_TEXT);

        AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle("Comment")

                .setView(mviewdialog)

                .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mComment = input.getText().toString();

                        Log.d("DEBUG", "Comment " + mComment);
                    }
                })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }

    private String getDay() {
        Date mRightNow = new Date();

        Log.d(TAG, "Now : "+mRightNow);

        //Get today's date
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd/MM/yy");
        String dateResult = formater.format(mRightNow);
        return dateResult;
    }



}

