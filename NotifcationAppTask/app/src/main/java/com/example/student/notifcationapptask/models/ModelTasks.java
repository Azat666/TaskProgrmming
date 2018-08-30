package com.example.student.notifcationapptask.models;

public class ModelTasks {

    private int ischekid;
    private String name;
    private String time;
    private String date;
    private int image;

    public ModelTasks(String name, String time, String date, int image, int ischekid) {

        this.name = name;
        this.time = time;
        this.date = date;
        this.image = image;
        this.ischekid = ischekid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int isIschekid() {
        return ischekid;
    }

    public void setIschekid(int ischekid) {
        this.ischekid = ischekid;
    }
}
