package com.zjs.arkachiever;

public class Achievement {

    int id;
    String achievementTitle;
    double goal;
    double progress;
    String dataType;

    public Achievement(int id, String achievementTitle, double goal, double progress, String dataType) {
        this.id = id;
        this.achievementTitle = achievementTitle;
        this.goal = goal;
        this.progress = progress;
        this.dataType = dataType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAchievementTitle() {
        return achievementTitle;
    }

    public void setAchievementTitle(String achievementTitle) {
        this.achievementTitle = achievementTitle;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", achievementTitle='" + achievementTitle + '\'' +
                ", goal=" + goal +
                ", progress=" + progress +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
