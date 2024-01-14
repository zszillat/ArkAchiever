package com.apps.arkachiever3.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.arkachiever3.R;
import com.apps.arkachiever3.model.Achievement;
import com.apps.arkachiever3.model.Milestone;

import java.util.HashMap;
import java.util.List;

public class AchievementViewAdapter extends RecyclerView.Adapter<AchievementViewViewHolder> {

    private MainActivityAdapter.OnItemClickListener onItemClickListener;
    private List<Milestone> milestones;
    private Achievement achievement;
    private Context context;

    public AchievementViewAdapter(Context context, List<Milestone> milestones, Achievement achievement) {
        this.context = context;
        this.milestones = milestones;
        this.achievement = achievement;
    }

    @NonNull
    @Override
    public AchievementViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AchievementViewViewHolder(LayoutInflater.from(context).inflate(R.layout.achievement_view_recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewViewHolder holder, int position) {
        Milestone milestone = milestones.get(position);
        holder.textViewMilestoneName.setText(milestone.getMilestoneName());
        holder.textViewMilestoneGoal.setText(String.valueOf(milestone.getGoal()));
        holder.textViewMilestoneDescription.setText(milestone.getMilestoneDescription());
        holder.imageView.setImageResource(milestone.getResource(context));
        holder.progressBar.getProgressDrawable().setColorFilter(Color.rgb(59,161, 252), android.graphics.PorterDuff.Mode.SRC_IN);

        if (achievement.getProgress() < milestone.getGoal()) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0);
            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
            holder.imageView.setColorFilter(colorFilter);
        }

        double progress = achievement.getProgress()/milestone.getGoal() * 100;


        if (progress >= 100) {
            holder.progressBar.setProgress(100);
            //TODO Make photo black and white
        } else if (progress <=0) {
            holder.progressBar.setProgress(0);
        }
        holder.progressBar.setProgress((int) progress);
    }

    @Override
    public int getItemCount() {
        return milestones.size();

    }

}
