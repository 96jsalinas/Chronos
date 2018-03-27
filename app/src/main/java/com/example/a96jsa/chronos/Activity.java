package com.example.a96jsa.chronos;


import java.util.Date;

/**
 * Created by kevin on 3/27/2018.
 */

public class Activity {
    private long _id;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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

    private String activityName;
    private Date recordingDate;
    private int totalTime;
    private static int DEFAULT_TIME = 1000;

    public Activity(){
        activityName = "Default name";
        recordingDate = new Date();
        totalTime = DEFAULT_TIME;

    }
    public Activity(String name){
        activityName = name;
        recordingDate = new Date();
        totalTime = DEFAULT_TIME;
    }
}
