package com.apps.arkachiever3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class NewAchievementActivityPageTwo extends AppCompatActivity {

    String achievementName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_achievement_page_two);

        //Intent
        Intent intent = getIntent();
        achievementName = intent.getStringExtra("name");

    }
}