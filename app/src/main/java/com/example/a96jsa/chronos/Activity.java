package com.example.a96jsa.chronos;

import java.util.Date;

/**
 * Created by kevin on 4/17/2018.
 */

public class Activity {
    private String activityName;
    private String categoryName;
    private int totalTime;
    private Date recordingDate;

    public boolean isTrackingState() {
        return trackingState;
    }

    public void setTrackingState(boolean trackingState) {
        this.trackingState = trackingState;
    }

    private boolean trackingState;
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }


}
