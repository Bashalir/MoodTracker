package com.oc.bashalir.moodtracker.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oc.bashalir.moodtracker.R;

/**
 * Created by Sumer on 10/03/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {



    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_bar,parent,false);

        return new HistoryViewHolder(view);
    }

    @Override
     public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {

     /*   Mood mood = mList.get(position);
        holder.display(mood);*/

    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        private final CardView backMood;
        private final ImageView imgMood;

        public HistoryViewHolder(final View itemView) {
            super (itemView);
            imgMood=(itemView.findViewById(R.id.list_cell_image_mood_img));
            backMood = (itemView.findViewById(R.id.list_cell_back_mood_cv));
        }

        private void display (Mood mood) {


            Log.d("Debug","Color :"+mood.getBackColor());

            imgMood.setImageResource(mood.getSmiley());
            backMood.setCardBackgroundColor(mood.getBackColor());

        }
    }


}
