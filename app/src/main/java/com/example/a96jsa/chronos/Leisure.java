package com.example.a96jsa.chronos;

/**
 * Created by Christian on 3/28/2018.
 */

public class Leisure {

    public Long _id;
    public String leisureType;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getLeisureType() {
        return leisureType;
    }

    public void setLeisureType(String leisureType) {
        this.leisureType = leisureType;
    }


    public Leisure(String leisureType) {
        this.leisureType = leisureType;
    }
}
