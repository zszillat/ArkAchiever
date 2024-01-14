package com.apps.arkachiever3.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.arkachiever3.R;

public class AchievementViewViewHolder extends RecyclerView.ViewHolder {

    TextView textViewMilestoneName;
    TextView textViewMilestoneDescription;
    TextView textViewMilestoneGoal;
    ProgressBar progressBar;
    ImageView imageView;

    public AchievementViewViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewMilestoneName = itemView.findViewById(R.id.milestoneName);
        textViewMilestoneDescription = itemView.findViewById(R.id.milestoneDescription);
        textViewMilestoneGoal = itemView.findViewById(R.id.milestoneGoal);
        progressBar = itemView.findViewById(R.id.progressBar);
        imageView = itemView.findViewById(R.id.imageViewAchievement);
    }

}
