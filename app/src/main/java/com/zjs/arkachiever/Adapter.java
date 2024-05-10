package com.zjs.arkachiever;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    //Variables
    List<Achievement> achievements = new LinkedList<>();
    Context context;

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public Adapter(List<Achievement> achievements, Context context) {
        this.achievements = achievements;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Achievement currentAchievement = achievements.get(position);
        int progress =(int) (currentAchievement.getProgress()/currentAchievement.getGoal() * 100);

        holder.textViewTitle.setText(currentAchievement.getAchievementTitle());
        holder.progressBar.setProgress(progress);
        holder.textViewProgress.setText(NumFormat.moneyFormat(currentAchievement.getProgress()) + " / " + NumFormat.moneyFormat(currentAchievement.getGoal()) + " " + currentAchievement.getDataType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
