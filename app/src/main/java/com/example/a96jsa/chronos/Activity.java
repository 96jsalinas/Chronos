package com.example.a96jsa.chronos;


import java.util.Date;

/**
 * Created by kevin on 3/27/2018.
 */

public class Activity {
    private Long _id;
    private String activityName;
    private String categoryName;
    private Date recordingDate;
    private int totalTime;
    private static int DEFAULT_TIME = 1000;

    public Activity(){
        activityName = "Default activity ";
        categoryName = "Default category";
        recordingDate = new Date();
        totalTime = DEFAULT_TIME;

    }
    public Activity(String activityName){
        this.activityName = activityName;
        recordingDate = new Date();
        totalTime = DEFAULT_TIME;
    }
    public Activity(String activityName, String categoryName){
        this.activityName = activityName;
        this.categoryName = categoryName;
        recordingDate = new Date();
        totalTime = DEFAULT_TIME;
    }
    public Activity(String activityName, String categoryName, int totalTime){
        this.activityName = activityName;
        this.categoryName = categoryName;
        recordingDate = new Date();
        this.totalTime = totalTime;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public static int getDefaultTime() {
        return DEFAULT_TIME;
    }

    public static void setDefaultTime(int defaultTime) {
        DEFAULT_TIME = defaultTime;
    }
}
