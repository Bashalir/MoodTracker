package com.oc.bashalir.moodtracker.model;

import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oc.bashalir.moodtracker.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.oc.bashalir.moodtracker.controller.MainActivity.LIST_MOOD;

/**
 * Created by Sumer on 10/03/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private SharedPreferences mPreferences;
    protected List<MoodDay> mMoodDayList=new ArrayList<>();
    public static final String TAG = "HistoryActivity";


    public HistoryAdapter(String json) {

            List<MoodDay> moodDayList=new ArrayList<>();
            Gson gson;
            gson= new Gson();


            Log.d(TAG,json);

            if (json !=null)
                mMoodDayList=gson.fromJson(json, new TypeToken<ArrayList<MoodDay>>() {}.getType());

            for (MoodDay m:mMoodDayList)
            { Log.d(TAG,"ListMood :"+m.getPosition()+" "+m.getDay()+" "+m.getComment());

            }
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

            // THE MOOD LIST DATA
            Mood mood1= new Mood(R.drawable.smiley_sad,R.color.faded_red,"Sad",R.raw.sad);
            Mood mood2= new Mood(R.drawable.smiley_disappointed,R.color.warm_grey,"Disappointed",R.raw.disappointed);
            Mood mood3= new Mood(R.drawable.smiley_normal,R.color.cornflower_blue_65, "Normal",R.raw.normal);
            Mood mood4= new Mood(R.drawable.smiley_happy,R.color.light_sage,"Happy",R.raw.happy);
            Mood mood5= new Mood(R.drawable.smiley_super_happy,R.color.banana_yellow,"Super Happy",R.raw.superhappy);

            ArrayList<Mood> mList= new ArrayList<>(Arrays.asList(mood1, mood2, mood3, mood4, mood5));

            Mood mood = mList.get(position);

            backHistory.setCardBackgroundColor(mood.getBackColor());

        }
    }


}
