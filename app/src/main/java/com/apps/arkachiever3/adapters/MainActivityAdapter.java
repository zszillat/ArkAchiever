package com.apps.arkachiever3.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.arkachiever3.DatabaseHelper;
import com.apps.arkachiever3.activities.MainActivity;
import com.apps.arkachiever3.R;
import com.apps.arkachiever3.model.Achievement;
import com.apps.arkachiever3.model.Milestone;

import java.util.HashMap;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Milestone> milestones;
    private HashMap<Integer, Achievement> achievementHashMap;
    private Context context;
    int INCREMENT = 1;

    public MainActivityAdapter(Context context, List<Milestone> milestones, OnItemClickListener onItemClickListener, HashMap<Integer,Achievement> achievementHashMap) {
        this.context = context;
        this.milestones = milestones;
        this.onItemClickListener = onItemClickListener;
        this.achievementHashMap = achievementHashMap;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainActivityViewHolder(LayoutInflater.from(context).inflate(R.layout.main_activity_recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position)
    {

        //Setting Recycler View Components
        int currentAchievementID = milestones.get(position).getAchievementID();
        setComponents(holder, currentAchievementID);

        //OnClickListener for when the recycler view is pressed
        holder.itemView.setOnClickListener((view -> {
            if (onItemClickListener != null) {
                int adapterPosition = holder.getAdapterPosition();
                onItemClickListener.onItemClick(adapterPosition);
            }
        }));

        //OnClickListener addButton
        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbh = new DatabaseHelper(view.getContext());
                Achievement buttonAchievement = dbh.getAchievementById(currentAchievementID);
                double newValue = buttonAchievement.getProgress() - INCREMENT;
                dbh.updateAchievementProgress(buttonAchievement, (int) newValue);

                setComponents(holder, currentAchievementID);
            }
        });

        //DEBUG BUTTON
        //OnClickListener minusButton
        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper dbh = new DatabaseHelper(view.getContext());
                Achievement buttonAchievement = dbh.getAchievementById(currentAchievementID);
                double newValue = buttonAchievement.getProgress() + INCREMENT;
                dbh.updateAchievementProgress(buttonAchievement, (int) newValue);

                setComponents(holder, currentAchievementID);

            }
        });
    }

    @Override
    public int getItemCount() {
        return milestones.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setComponents(MainActivityViewHolder holder, int achievementID) {
        DatabaseHelper dbh = new DatabaseHelper(context);

        Achievement achievement = dbh.getAchievementById(achievementID);
        Milestone milestone = achievement.getCurrentMilestone(dbh.getMilestonesByAchievementID(achievementID));
        double goal = milestone.getGoal();
        double progress = achievement.getProgress();
        double barProgress = 100 * (progress/goal);

        holder.textViewMilestoneName.setText(milestone.getMilestoneName());
        holder.textViewMilestoneDescription.setText(milestone.getMilestoneDescription());
        holder.textViewMilestoneGoal.setText(MainActivity.roundString(progress, 2) + " / " + MainActivity.roundString(goal,2) + " " + achievement.getDataType());
        holder.progressBar.setProgress((int) barProgress);
        holder.progressBar.getProgressDrawable().setColorFilter(Color.rgb(59,161, 252), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.imageView.setImageResource(milestone.getResource(context));

        if (achievement.getProgress() < milestone.getGoal()) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0);
            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
            holder.imageView.setColorFilter(colorFilter);
        }
    }

}
