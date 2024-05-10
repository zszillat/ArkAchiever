package com.zjs.arkachiever;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    //Adapter
    RecyclerView recyclerView;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button buttonNew;

    List<Achievement> achievements = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbh = new DatabaseHelper(this);
        achievements = dbh.getAchievements();

        //Recycler View
        recyclerView = this.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(achievements, this);
        recyclerView.setAdapter(adapter);

        buttonNew = this.findViewById(R.id.buttonNew);

        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAchievement.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        true
                );


                findViewById(R.id.main).post(new Runnable() {
                    public void run() {
                        popupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);

                        TextView popUpTextViewTitle = popupView.findViewById(R.id.popUpTextViewTitle);
                        EditText popUpEditTextProgress = popupView.findViewById(R.id.popUpEditTextProgress);
                        Button popUpButtonUpdate = popupView.findViewById(R.id.popUpButtonUpdate);

                        Achievement a = achievements.get(position);
                        popUpTextViewTitle.setText(a.getAchievementTitle());
                        popUpEditTextProgress.setText(String.valueOf(a.getProgress()));

                        popUpButtonUpdate.setOnClickListener(v -> {

                            try {
                                DatabaseHelper dbh = new DatabaseHelper(MainActivity.this);
                                Double d = Double.parseDouble(popUpEditTextProgress.getText().toString());
                                dbh.updateAchievement(a.getId(), d);
                                adapter.achievements.get(position).setProgress(d);
                                adapter.notifyItemChanged(position);



                                popupWindow.dismiss();
                            } catch (NumberFormatException e) {}



                        });


                    }
                });




            }
        });





    }

    public void makeAchievements() {
        achievements.add(new Achievement(0,"Savings", 100000.00, 47000,"USD"));
        achievements.add(new Achievement(1,"cats", 5.00, 0,"cats"));

        DatabaseHelper db = new DatabaseHelper(this);
        for (Achievement a : achievements) {
            db.addAchievement(a);
        }
    }


}