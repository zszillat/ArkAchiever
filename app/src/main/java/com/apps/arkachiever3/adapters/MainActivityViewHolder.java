package com.apps.arkachiever3.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.arkachiever3.R;

public class MainActivityViewHolder extends RecyclerView.ViewHolder {

    TextView textViewMilestoneName;
    TextView textViewMilestoneDescription;
    TextView textViewMilestoneGoal;
    ProgressBar progressBar;
    Button buttonPlus;
    Button buttonMinus;
    ImageView imageView;

    public MainActivityViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewMilestoneName = itemView.findViewById(R.id.milestoneName);
        textViewMilestoneDescription = itemView.findViewById(R.id.milestoneDescription);
        textViewMilestoneGoal = itemView.findViewById(R.id.milestoneGoal);
        progressBar = itemView.findViewById(R.id.milestoneProgress);
        buttonPlus = itemView.findViewById(R.id.milestonePlus);
        buttonMinus = itemView.findViewById(R.id.milestoneMinus);
        imageView = itemView.findViewById(R.id.imageView);

    }

}
