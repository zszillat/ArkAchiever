//TODO When an achievement is completed confetti is thrown
package com.apps.arkachiever3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.apps.arkachiever3.DatabaseHelper;
import com.apps.arkachiever3.R;
import com.apps.arkachiever3.activities.new_achievement.NewAchievementActivityPageOne;
import com.apps.arkachiever3.adapters.MainActivityAdapter;
import com.apps.arkachiever3.model.Achievement;
import com.apps.arkachiever3.model.DatabasePopulator;
import com.apps.arkachiever3.model.Milestone;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainActivityAdapter.OnItemClickListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    List<Milestone> milestones = new ArrayList<>();
    List<Achievement> achievements = new ArrayList<>();
    HashMap<Integer, Achievement> achievementHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("reddit", "hereere");

        //Assign Components
        recyclerView = findViewById(R.id.recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHelper dbh = new DatabaseHelper(this);
        achievements = dbh.getAchievements();
        populateHashMap();

        //Loop finds the current milestone for each achievement
        for (Achievement achievement : achievements) {
            Integer achievementID = achievement.getAchievementID();
            List<Milestone> recyclerViewMilestones = dbh.getMilestonesByAchievementID(achievementID);
            Milestone currentMilestone = achievement.getCurrentMilestone(recyclerViewMilestones);
            milestones.add(currentMilestone);
        }

        //Recycler View Setup
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityAdapter adapter = new MainActivityAdapter(this, milestones,this, achievementHashMap);
        recyclerView.setAdapter(adapter);



    }

    public void populateHashMap() {
        for (Achievement achievement : achievements) {
            achievementHashMap.put(achievement.getAchievementID(),achievement);
        }
    }

    public static String roundString(double number, int decimalPlaces) {
        // Round to the specified decimal places
        double roundedNumber = Math.round(number * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);

        // Convert to string
        String result = Double.toString(roundedNumber);

        // Remove trailing zeros and decimal point if they exist
        result = result.indexOf('.') < 0 ? result : result.replaceAll("0*$", "").replaceAll("\\.$", "");

        return result;
    }



    public void databasePopulate() {

        DatabaseHelper dbh = new DatabaseHelper(MainActivity.this);
        for (Achievement achievement : DatabasePopulator.achievementList()) {
            dbh.addAchievement(achievement);

        }

        for (Milestone milestone : DatabasePopulator.milestoneList()) {
            dbh.addMilestone(milestone);
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, AchievementActivity.class);
        intent.putExtra("achievementID", achievements.get(position).getAchievementID());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, NewAchievementActivityPageOne.class);
            Log.d("reddit", "hereere");
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



}