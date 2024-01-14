//TODO Plus and Minus button to adjust the progress
//TODO EditText for editing the progress manually
package com.apps.arkachiever3.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.apps.arkachiever3.DatabaseHelper;
import com.apps.arkachiever3.R;
import com.apps.arkachiever3.adapters.AchievementViewAdapter;
import com.apps.arkachiever3.model.Achievement;
import com.apps.arkachiever3.model.Milestone;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AchievementActivity extends AppCompatActivity {
    private TextView textViewAchievementName;
    EditText editTextProgress;
    Button buttonUpdate;
    ProgressBar progressBar;
    TextView textViewProgressBarProgress;

    RecyclerView recyclerView;

    private Achievement currentAchievement;
    private List<Milestone> sortedMilestones  = new ArrayList<>();

    private int achievementID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_view);

        //Intent
        Intent intent = getIntent();
        achievementID = intent.getIntExtra("achievementID", -1);

        //Assign Components
        textViewAchievementName = this.findViewById(R.id.textViewDescription);
        editTextProgress = this.findViewById(R.id.editTextProgress);
        buttonUpdate = this.findViewById(R.id.buttonUpdate);
        progressBar = this.findViewById(R.id.progressBar);
        textViewProgressBarProgress = this.findViewById(R.id.textViewProgressBarProgress);
        recyclerView = this.findViewById(R.id.recyclerView);

        setComponents();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double newValue = Double.valueOf(editTextProgress.getText().toString());
                DatabaseHelper dbh = new DatabaseHelper(AchievementActivity.this);
                dbh.updateAchievementProgress(currentAchievement, newValue);
                setComponents();
            }
        });
    }

    //Sets or Updates achievement values
    public void setComponents() {
        DatabaseHelper dbh = new DatabaseHelper(this);
        currentAchievement = dbh.getAchievementById(achievementID);
        sortedMilestones = Milestone.sortByGoal(dbh.getMilestonesByAchievementID(achievementID));
        double completedAchievements = 0.00;
        //find out how many milestones are complete
        for (Milestone milestone : sortedMilestones) {
            if (currentAchievement.getProgress() < milestone.getGoal()) {
                break;
            } else {
                completedAchievements++;
            }
        }
        double progressBarProgress = completedAchievements/Double.valueOf(sortedMilestones.size())*100.00;
        textViewAchievementName.setText(currentAchievement.getAchievementName().toUpperCase());
        editTextProgress.setText(MainActivity.roundString(currentAchievement.getProgress(), 2));
        progressBar.setProgress((int) progressBarProgress);
        textViewProgressBarProgress.setText(MainActivity.roundString(completedAchievements, 2) + " / " + (sortedMilestones.size()) + " achievements");
        progressBar.getProgressDrawable().setColorFilter(Color.rgb(59,161, 252), android.graphics.PorterDuff.Mode.SRC_IN);

        //recycler view setup
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AchievementViewAdapter adapter = new AchievementViewAdapter(this, sortedMilestones , currentAchievement);
        recyclerView.setAdapter(adapter);
    }
}