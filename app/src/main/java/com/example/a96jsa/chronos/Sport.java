package com.example.a96jsa.chronos;

/**
 * Created by Christian on 3/28/2018.
 */

public class Sport {

    public Long _id;
    public  String sportType;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public Sport(String sportType) {
        this.sportType = sportType;
    }
}
