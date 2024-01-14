package com.apps.arkachiever3.model;

import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Milestone {
    private int milestoneID; //PRIMARY KEY
    private int achievementID; //FOREIGN KEY
    private String milestoneName;
    private  String milestoneDescription; //displayed instead of progress bar if it is qualitative data
    private double goal;
    private String resource;

    public Milestone(int milestoneID, int achievementID, String milestoneName, String description, double goal, String resource) {
        this.milestoneID = milestoneID;
        this.achievementID = achievementID;
        this.milestoneName = milestoneName;
        this.milestoneDescription = milestoneDescription;
        this.goal = goal;
        this.resource = resource;
    }

    public Integer calculateProgress(double progress) {
        //TODO
        double percent = progress / this.goal;
        double percentButMultipliedByOneHundred = percent * 100;
        return (int) percentButMultipliedByOneHundred;
    }

    public static List<Milestone> sortByGoal(List<Milestone> milestoneList) {
        // Define a comparator for Milestone objects based on the "goal" attribute
        Comparator<Milestone> goalComparator = Comparator.comparingDouble(Milestone::getGoal);

        // Sort the list using the comparator
        Collections.sort(milestoneList, goalComparator);
        Log.d("milestone", "");
        return milestoneList;
    }

    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
    }

    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    public void setMilestoneDescription(String milestoneDescription) {
        this.milestoneDescription = milestoneDescription;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public String getResource() {
        return resource;
    }
    public Integer getResource(Context context) {
        return context.getResources().getIdentifier(resource.substring(0,resource.length()-4), "drawable", context.getPackageName());
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
