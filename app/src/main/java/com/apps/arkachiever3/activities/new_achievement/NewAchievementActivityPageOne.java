package com.apps.arkachiever3.activities.new_achievement;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.apps.arkachiever3.R;

public class NewAchievementActivityPageOne extends AppCompatActivity {

    Button buttonNext;
    EditText editTextName;
    String achievementName;
    Spinner spinner;
    String[] spinnerItems = {"Currency", "Time", "Custom"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_achievement_page_one);

        //Assign Components
        buttonNext = this.findViewById(R.id.buttonNext);
        editTextName = this.findViewById(R.id.editTextName);
        spinner = this.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                achievementName = editTextName.getText().toString();
                if (isAchievementNameValid(achievementName)) {
                    Intent intent = new Intent(NewAchievementActivityPageOne.this, NewAchievementActivityPageTwo.class);
                    intent.putExtra("achievementName", achievementName);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewAchievementActivityPageOne.this, "doesn't meet requirements", Toast.LENGTH_SHORT).show();
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = spinnerItems[position];
                switch (selectedItem) {
                    case "currency":
                        currencySelected();
                        break;
                    case "Time":
                        timeSelected();
                        break;
                    case "Custom":
                        customSelected();
                        break;
                    
                    
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

    }

    private void customSelected() {
    }

    private void timeSelected() {
    }

    private void currencySelected() {
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