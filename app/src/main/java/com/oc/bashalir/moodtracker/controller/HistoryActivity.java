package com.oc.bashalir.moodtracker.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.HistoryAdapter;
import com.oc.bashalir.moodtracker.model.MoodDay;

import java.util.ArrayList;
import java.util.List;



public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mHistoryRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(new HistoryAdapter());

    }

}
