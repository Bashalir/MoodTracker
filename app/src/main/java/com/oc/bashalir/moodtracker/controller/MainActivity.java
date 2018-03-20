package com.oc.bashalir.moodtracker.controller;

import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.oc.bashalir.moodtracker.R;
import com.oc.bashalir.moodtracker.model.MoodAdapter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mListMoodRecyclerView;
    private ImageView mCommentButton;
    private ImageView mHistoryButton;
    private SharedPreferences mPreferences;
    private int mMoodPosition=2;
    private MoodAdapter mAdapter;


    private void configureRecyclerView(){

        mAdapter=new MoodAdapter();
        mListMoodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListMoodRecyclerView.setAdapter(mAdapter);

        mListMoodRecyclerView.scrollToPosition(mMoodPosition);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mListMoodRecyclerView);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListMoodRecyclerView = findViewById(R.id.activity_main_listMood_rv);
        mCommentButton = findViewById(R.id.activity_main_comment_btn);
        mHistoryButton = findViewById(R.id.activity_main_history_btn);

        mPreferences=getPreferences(MODE_PRIVATE);

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();


        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComment();
            }
        });

    }


   private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mListMoodRecyclerView,R.layout.list_cell)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.d("Debug","Position :"+position);
                    }
                });
   }

    private void addComment() {

            final EditText input = new EditText(this);

            LayoutInflater inflater = LayoutInflater.from(this);
            View mviewdialog = inflater.inflate(R.layout.comment_dialog,null,false);

            input.setInputType(InputType.TYPE_CLASS_TEXT );
            AlertDialog builder = new AlertDialog.Builder(this)
                    .setTitle("Comment")

                    .setView(mviewdialog)

                    .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mPreferences.edit().putString("comment",input.toString()).apply();
                        Log.d("DEBUG","Comment "+input.getText().toString());
                    }
                    })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                .show();

        }

    }

