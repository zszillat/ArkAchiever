package com.apps.arkachiever3.activities.new_achievement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.apps.arkachiever3.R;

public class NewAchievementActivityPageTwo extends AppCompatActivity {

    TextView textViewNewAchievement;
    String achievementName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_achievement_page_two);

        //Intent
        Intent intent = getIntent();
        achievementName = intent.getStringExtra("achievementName");

        //Associate Components
        textViewNewAchievement = this.findViewById(R.id.textViewNewAchievement);

        textViewNewAchievement.setText(achievementName);

    }
}