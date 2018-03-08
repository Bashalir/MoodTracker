package com.oc.bashalir.moodtracker.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        mListMoodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListMoodRecyclerView.setAdapter(new MoodAdapter());


        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComment();
            }
        });

    }


        private void addComment() {

            final EditText input = new EditText(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT );

            AlertDialog.Builder builderComment = builder.setView(input, 50, 0, 50, 0);
            builder.setTitle("Comment")
                  .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();

        }

    }

