package com.apps.arkachiever3.activities.new_achievement;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.arkachiever3.R;

public class NewAchievementActivityPageOne extends AppCompatActivity {

    Button buttonNext;
    EditText editTextName;
    String achievementName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_a_page_1);

        //Assign Components
        buttonNext = this.findViewById(R.id.buttonNext);
        editTextName = this.findViewById(R.id.editTextName);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                achievementName = editTextName.getText().toString();
                if (isAchievementNameValid(achievementName)) {
                    Intent intent = new Intent(NewAchievementActivityPageOne.this, NewAchievementActivityPageTwo.class);
                    intent.putExtra("name", achievementName);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewAchievementActivityPageOne.this, "doesn't meet requirements", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isAchievementNameValid(String achievementName) {
        //Criteria
        Integer minChar = 1;
        Integer maxChar = 25;

        Integer len = achievementName.length();
        if (len < minChar || len > maxChar) {
            return false;
        }

        return true;
    }

}