package com.oc.bashalir.moodtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import com.oc.bashalir.moodtracker.R;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mListMoodRecyclerView;
    private Button mCommentButton;
    private Button mHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListMoodRecyclerView = findViewById(R.id.activity_main_listMood_rv);
        mCommentButton = findViewById(R.id.activity_main_comment_btn);
        mHistoryButton = findViewById(R.id.activity_main_history_btn);

        mListMoodRecyclerView.setLayoutManager (new LinearLayoutManager(this));
        mListMoodRecyclerView.setAdapter(new MoodAdapter());

    }
}
