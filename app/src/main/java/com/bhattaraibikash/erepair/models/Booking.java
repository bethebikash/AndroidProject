package com.bhattaraibikash.erepair.models;

public class Booking {
    private String problem;
    private String date;
    private String time;
    private String location;
    private String service;

    public Booking(String problem, String date, String time, String location, String service) {
        this.problem = problem;
        this.date = date;
        this.time = time;
        this.location = location;
        this.service = service;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
