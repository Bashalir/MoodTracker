package com.oc.bashalir.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.bashalir.moodtracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Sumer on 10/03/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private SharedPreferences mPreferences;
    protected List<MoodDay> mMoodDayList=new ArrayList<>();
    public static final String TAG = "HistoryActivity";
    private static TypedArray tColor;
    private int mHeight;
    private int mWidth;
    private Context mContext;

    public HistoryAdapter(List<MoodDay> listMoodDay, TypedArray Color, int x, int y) {

        mHeight=x;
        mWidth=y;
        mMoodDayList=listMoodDay;
        tColor=Color;
    }

    @Override
    public int getItemCount() {

        return mMoodDayList.size();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_bar,parent,false);

        return new HistoryViewHolder(view);
    }

    @Override
     public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {

        MoodDay moodDay = mMoodDayList.get(position);
        holder.display(moodDay);

    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        private final CardView backHistory;
        private final ImageView commentHistory;
        private final TextView dateHistory;
        private String mComment;

        public HistoryViewHolder(final View itemView) {
            super (itemView);
            backHistory = (itemView.findViewById(R.id.list_bar_bar_cv));
            commentHistory=(itemView.findViewById(R.id.list_bar_comment_btn));
            dateHistory=(itemView.findViewById(R.id.list_bar_date_txt));
        }

        private void display (MoodDay moodDay) {
            commentHistory.setVisibility(View.INVISIBLE);
            mComment= moodDay.getComment();

            if(moodDay.getComment()!=null){commentHistory.setVisibility(View.VISIBLE);}

            dateHistory.setText(formatDate(moodDay.getDay()));
            int position=moodDay.getPosition();

            backHistory.setBackgroundResource(tColor.getResourceId(position, position));
            ViewGroup.LayoutParams params =backHistory.getLayoutParams();
            params.height=mHeight/7;
            params.width=(mWidth/5)*(position+1);
            backHistory.setLayoutParams(params);

           commentHistory.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(commentHistory.getContext(),mComment, Toast.LENGTH_SHORT).show();
               }
           });


        }
    }
    public String formatDate(Date date) {

        //Get format date
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd/MM/yy");

        String dateResult = formater.format(date);
        return dateResult;
    }


}
