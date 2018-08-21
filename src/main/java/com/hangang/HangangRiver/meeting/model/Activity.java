package com.hangang.HangangRiver.meeting.model;

public class Activity {
    private int activity_seq;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActivity_seq() {
        return activity_seq;
    }

    public void setActivity_seq(int activity_seq) {
        this.activity_seq = activity_seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
