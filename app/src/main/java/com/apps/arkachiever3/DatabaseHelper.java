package com.apps.arkachiever3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.apps.arkachiever3.model.Achievement;
import com.apps.arkachiever3.model.Milestone;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String ACHIEVEMENT_TABLE = "achievement_table";
    public static final String MILESTONE_TABLE = "milestone_table";
    //ACHIEVEMENT TABLE VARIABLES
    public static final String COLUMN_ACHIEVEMENT_NAME = "achievement_name";
    public static final String COLUMN_ACHIEVEMENT_DESCRIPTION = "achievement_description";
    public static final String COLUMN_STARTING_VALUE = "starting_value";

    public static final String COLUMN_INCLUDE_GOAL = "include_goal";
    public static final String COLUMN_DATE_COMPLETED = "date_completed";
    public static final String COLUMN_PROGRESS = "progress";
    public static final String COLUMN_PROGRESS_CURRENT_DATE = COLUMN_PROGRESS + "_current_date";
    //MILESTONE TABLE VARIABLES
    public static final String COLUMN_ACHIEVEMENT_ID = "achievement_id";
    public static final String COLUMN_MILESTONE_NAME = "milestone_name";
    public static final String COLUMN_MILESTONE_DESCRIPTION = "milestone_description";
    public static final String COLUMN_GOAL = "goal";
    public static final String COLUMN_RESOURCE = "resource";
    public static final String COLUMN_DATA_TYPE = "data_type";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "ArkAchiever.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ACHIEVEMENT_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACHIEVEMENT_NAME + " TEXT, " + COLUMN_ACHIEVEMENT_DESCRIPTION + " TEXT, " + COLUMN_DATA_TYPE + " TEXT, " + COLUMN_STARTING_VALUE + " REAL, " + COLUMN_INCLUDE_GOAL + " INTEGER, " + COLUMN_DATE_COMPLETED + " INTEGER, " + COLUMN_PROGRESS_CURRENT_DATE + " INTEGER, " + COLUMN_PROGRESS + " REAL)");
        db.execSQL("CREATE TABLE " + MILESTONE_TABLE + " (milestone_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACHIEVEMENT_ID + " INTEGER, " + COLUMN_MILESTONE_NAME + " TEXT, " + COLUMN_MILESTONE_DESCRIPTION + " TEXT, " + COLUMN_GOAL + " REAL, " + COLUMN_RESOURCE + " TEXT,  FOREIGN KEY (achievement_id) REFERENCES " + ACHIEVEMENT_TABLE + "(" + COLUMN_ACHIEVEMENT_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addAchievement(Achievement achievement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ACHIEVEMENT_NAME,achievement.getAchievementName());
        cv.put(COLUMN_ACHIEVEMENT_DESCRIPTION, achievement.getDescription());
        cv.put(COLUMN_DATA_TYPE, achievement.getDataType());
        cv.put(COLUMN_STARTING_VALUE, achievement.getStartingValue());
        cv.put(COLUMN_INCLUDE_GOAL, achievement.isIncludeGoal());
        cv.put(COLUMN_DATE_COMPLETED, achievement.getDateCompleted());
        cv.put(COLUMN_PROGRESS,achievement.getProgress());
        cv.put(COLUMN_PROGRESS_CURRENT_DATE,achievement.isProgressCurrentDate());

        long insert = db.insert(ACHIEVEMENT_TABLE, null, cv);
        return insert != -1;
    }

    public boolean updateAchievementProgress(Achievement achievement, double newValue) {

        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "UPDATE " + ACHIEVEMENT_TABLE + " SET " + COLUMN_PROGRESS + " = ? WHERE id = ?";
        if (newValue < achievement.getStartingValue()) {
            db.execSQL(queryString, new Object[]{achievement.getStartingValue(), achievement.getAchievementID()});
        } else {
            db.execSQL(queryString, new Object[]{newValue, achievement.getAchievementID()});
        }

        db.close();

        return true;
    }

    public List<Achievement> getAchievements() {
        List<Achievement> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ACHIEVEMENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int achievementID = cursor.getInt(0);
                String achievementName = cursor.getString(1);
                String achievementDescription = cursor.getString(2);
                String dataType = cursor.getString(3);
                Double startingValue = cursor.getDouble(4);
                Boolean includeGoal = cursor.getInt(5) == 1;
                Integer dateCompleted= cursor.getInt(6);
                Boolean isProgressCurrentDate = cursor.getInt(7) == 1;
                Double progress = cursor.getDouble(8);

                Achievement newAchievement = new Achievement(achievementID, achievementName, achievementDescription, dataType, startingValue, includeGoal, dateCompleted, isProgressCurrentDate, progress);
                returnList.add(newAchievement);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public Achievement getAchievementById(int achievementID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Achievement achievement = null;

        String queryString = "SELECT * FROM " + ACHIEVEMENT_TABLE + " WHERE id = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{String.valueOf(achievementID)});

        if (cursor.moveToFirst()) {
            String achievementName = cursor.getString(1);
            String achievementDescription = cursor.getString(2);
            String dataType = cursor.getString(3);
            Double startingValue = cursor.getDouble(4);
            Boolean includeGoal = cursor.getInt(5) == 1;
            Integer dateCompleted = cursor.getInt(6);
            Boolean isProgressCurrentDate = cursor.getInt(7) == 1;
            Double progress = cursor.getDouble(8);

            achievement = new Achievement(achievementID, achievementName, achievementDescription, dataType, startingValue, includeGoal, dateCompleted, isProgressCurrentDate, progress);
        }

        cursor.close();
        db.close();

        return achievement;
    }


    public boolean addMilestone(Milestone milestone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACHIEVEMENT_ID, milestone.getAchievementID());
        cv.put(COLUMN_MILESTONE_NAME, milestone.getMilestoneName());
        cv.put(COLUMN_MILESTONE_DESCRIPTION, milestone.getMilestoneDescription());
        cv.put(COLUMN_GOAL, milestone.getGoal());
        cv.put(COLUMN_RESOURCE, milestone.getResource());


        long insert = db.insert(MILESTONE_TABLE, null, cv);
        return insert != -1;
    }

    public List<Milestone> getMilestones() {

        List<Milestone> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + MILESTONE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int milestoneID = cursor.getInt(0);
                int achievementID = cursor.getInt(1);
                String milestoneName = cursor.getString(2);
                String milestoneDescription = cursor.getString(3);
                Double goal = cursor.getDouble(4);
                String resource = cursor.getString(5);


                Milestone newMilestone = new Milestone(milestoneID, achievementID, milestoneName, milestoneDescription, goal, resource);
                returnList.add(newMilestone);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public List<Milestone> getMilestonesByAchievementID(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Milestone> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + MILESTONE_TABLE + " WHERE achievement_id = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                int milestoneID = cursor.getInt(0);
                int achievementID = cursor.getInt(1);
                String milestoneName = cursor.getString(2);
                String milestoneDescription = cursor.getString(3);
                Double goal = cursor.getDouble(4);
                String resource = cursor.getString(5);

                Milestone newMilestone = new Milestone(milestoneID, achievementID, milestoneName, milestoneDescription, goal, resource);
                returnList.add(newMilestone);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

}
