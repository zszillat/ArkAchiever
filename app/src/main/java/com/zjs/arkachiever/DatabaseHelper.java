package com.zjs.arkachiever;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String ACHIEVEMENT_TABLE = "ACHIEVEMENT";
    public static final String COLUMN_ACHIEVEMENT_ID = "achievement_id";
    public static final String COLUMN_ACHIEVEMENT_TITLE = "achievement_title";
    public static final String COLUMN_GOAL = "goal";
    public static final String COLUMN_PROGRESS = "progress";
    public static final String COLUMN_DATA_TYPE = "dataType";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "ArkAchiever.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ACHIEVEMENT_TABLE + "(" + COLUMN_ACHIEVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACHIEVEMENT_TITLE + " TEXT, " + COLUMN_GOAL + " REAL, " + COLUMN_PROGRESS + " REAL, " + COLUMN_DATA_TYPE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addAchievement(Achievement a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ACHIEVEMENT_TITLE,a.getAchievementTitle());
        cv.put(COLUMN_GOAL,a.getGoal());
        cv.put(COLUMN_PROGRESS,a.getProgress());
        cv.put(COLUMN_DATA_TYPE,a.getDataType());

        long insert = db.insert(ACHIEVEMENT_TABLE, null, cv);
        return insert != -1;
    }

    public void updateAchievement(int achievementID, double newValue) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "UPDATE " + ACHIEVEMENT_TABLE + " SET " + COLUMN_PROGRESS + " = ? WHERE " + COLUMN_ACHIEVEMENT_ID + " = ?";
        db.execSQL(queryString, new Object[]{newValue, achievementID});
        db.close();
    }


    public List<Achievement> getAchievements() {
        List<Achievement> achievements = new LinkedList<>();

        String queryString = "SELECT * FROM " + ACHIEVEMENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int achievementID = cursor.getInt(0);
                String achievementTitle = cursor.getString(1);
                double goal = cursor.getDouble(2);
                double progress = cursor.getDouble(3);
                String dataType = cursor.getString(4);

                achievements.add(new Achievement(achievementID, achievementTitle, goal, progress, dataType));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return achievements;
    }


}
