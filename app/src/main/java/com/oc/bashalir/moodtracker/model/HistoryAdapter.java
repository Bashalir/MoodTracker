package com.oc.bashalir.moodtracker.model;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.bashalir.moodtracker.R;

import java.util.ArrayList;
import java.util.Arrays;
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

    public HistoryAdapter(List<MoodDay> listMoodDay, TypedArray Color, int x, int y) {

        mHeight=x;
        mWidth=y;
        mMoodDayList=listMoodDay;
        tColor=Color;
        Log.d(TAG,"Screen Dimensions :"+mHeight+"x"+mWidth+" px");
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

        public HistoryViewHolder(final View itemView) {
            super (itemView);
            backHistory = (itemView.findViewById(R.id.list_bar_bar_cv));
        }

        private void display (MoodDay moodDay) {

            int position=moodDay.getPosition();

            backHistory.setBackgroundResource(tColor.getResourceId(position, position));
            ViewGroup.LayoutParams params =backHistory.getLayoutParams();
            params.height=mHeight/7;
            params.width=(mWidth/5)*(position+1);
            backHistory.setLayoutParams(params);



        }
    }


}
