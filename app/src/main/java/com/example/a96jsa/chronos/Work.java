package com.example.a96jsa.chronos;

/**
 * Created by Christian on 3/28/2018.
 */

public class Work {

    public Long _id;
    public String workType;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Work(String workType) {
        this.workType = workType;
    }
}
