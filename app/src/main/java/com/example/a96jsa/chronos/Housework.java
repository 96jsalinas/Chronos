package com.example.a96jsa.chronos;

/**
 * Created by Christian on 3/28/2018.
 */

public class Housework {

    public Long _id;
    public String housworkType;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getHousworkType() {
        return housworkType;
    }

    public void setHousworkType(String housworkType) {
        this.housworkType = housworkType;
    }

    public Housework(Long _id) {
        this._id = _id;
    }
}
