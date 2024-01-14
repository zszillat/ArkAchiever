package com.apps.arkachiever3.model;

import android.util.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Achievement {
    private int achievementID;
    private String achievementName;
    private String description;
    private String dataType;
    private double startingValue;
    //TODO don't show goal if qualitative
    private boolean includeGoal; //the goals for qualitative achievements will just be 1, 2, 3, etc. there's no reason to display
    private int dateCompleted; //keeps track of the day completed. default is 0
    private boolean progressCurrentDate; //if true then the progress will always be equal to the current date
    private double progress;

    //CONSTRUCTOR
    public Achievement(int achievementID, String achievementName, String description, String dataType, double startingValue, boolean includeGoal, int dateCompleted, boolean progressCurrentDate, double progress) {
        this.achievementID = achievementID;
        this.achievementName = achievementName;
        this.description = description;
        this.dataType = dataType;
        this.startingValue = startingValue;
        this.includeGoal = includeGoal;
        this.dateCompleted = dateCompleted;
        this.progressCurrentDate = progressCurrentDate;
        this.progress = progress;
    }

    //STATIC
    public static double dateToInt(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate inputLocalDate = LocalDate.parse(inputDate, formatter);

        // Calculate days since year 2000
        LocalDate startDate = LocalDate.of(2000, 1, 1);
        long daysSince2000 = ChronoUnit.DAYS.between(startDate, inputLocalDate);
        return (double) daysSince2000;
    }

    public static String intToDate(double days) {
        // Calculate date from days since 2000
        LocalDate startDate = LocalDate.of(2000, 1, 1);
        LocalDate resultDate = startDate.plusDays((long) days);

        // Format result date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedResultDate = resultDate.format(formatter);

        return formattedResultDate;
    }

    public static String intToDate(int days) {
        // Calculate date from days since 2000
        LocalDate startDate = LocalDate.of(2000, 1, 1);
        LocalDate resultDate = startDate.plusDays((long) days);

        // Format result date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedResultDate = resultDate.format(formatter);

        return formattedResultDate;
    }

    //Non-Static Functions
    public Milestone getCurrentMilestone(List<Milestone> milestones) {
        //Sort the milestones by goal from lowest to highest
        milestones = Milestone.sortByGoal(milestones);

        //return the first milestone if progress is less than the 2nd milestones goal
        if (this.progress < milestones.get(0).getGoal()) {
            return milestones.get(0);
        }

        //loop through remaining milestones until progress is between the current milestone goal and the next milestone goal
        for (int i=1;i<milestones.size();i++) {
            double thisGoal = milestones.get(i-1).getGoal();
            double nextGoal = milestones.get(i).getGoal();

            if (progress >= thisGoal && progress < nextGoal) {
                return milestones.get(i);
            }
        }
        return milestones.get(milestones.size()-1);
    }

    //GETTERS AND SETTERS
    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public double getStartingValue() {
        return startingValue;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    public boolean isIncludeGoal() {
        return includeGoal;
    }

    public void setIncludeGoal(boolean includeGoal) {
        this.includeGoal = includeGoal;
    }

    public int getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(int dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public boolean isProgressCurrentDate() {
        return progressCurrentDate;
    }

    public void setProgressCurrentDate(boolean progressCurrentDate) {
        this.progressCurrentDate = progressCurrentDate;
    }

    public double getProgress() {
        //If the progress is the current date then it returns the current date otherwise it returns progress
        if (this.progressCurrentDate) {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String formattedDate = currentDate.format(formatter);
            return Achievement.dateToInt(formattedDate);
        }
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
