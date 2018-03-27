package com.example.a96jsa.chronos;


import java.util.Date;

/**
 * Created by kevin on 3/27/2018.
 */

public class Activity {
    private long _id;
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
