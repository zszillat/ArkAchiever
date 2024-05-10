package com.zjs.arkachiever;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewAchievement extends AppCompatActivity {

    Button buttonAdd;
    EditText editTextAchievementTitle;
    EditText editTextGoal;
    EditText editTextDataType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_achievement);

        buttonAdd = this.findViewById(R.id.buttonAdd);
        editTextAchievementTitle = this.findViewById(R.id.editTextAchievementTitle);
        editTextGoal = this.findViewById(R.id.editTextGoal);
        editTextDataType = this.findViewById(R.id.editTextDataType);

        buttonAdd.setOnClickListener(v -> {
            String title = editTextAchievementTitle.getText().toString();
            String dataType = editTextDataType.getText().toString();
            String stringGoal = editTextGoal.getText().toString();

            boolean valid = true;

            if (title.trim().isEmpty() ) {
                valid = false;
            }

            try {
                double goal = Double.parseDouble(stringGoal);
                DatabaseHelper databaseHelper = new DatabaseHelper(NewAchievement.this);
                databaseHelper.addAchievement(new Achievement(0, title, goal, 0.00, dataType));

                Intent intent = new Intent(NewAchievement.this, MainActivity.class);
                startActivity(intent);
            } catch (NumberFormatException e) {

            }

        });


    }

}