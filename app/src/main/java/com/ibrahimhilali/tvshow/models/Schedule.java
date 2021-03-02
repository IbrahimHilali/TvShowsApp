package com.ibrahimhilali.tvshow.models;

import java.util.ArrayList;

public class Schedule {
    protected String time;
    protected ArrayList<String> days;

    Schedule() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }
}
