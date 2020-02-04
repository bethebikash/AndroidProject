package com.bhattaraibikash.erepair.responses;

public class MyBookingResponse {
    private String _id;
    private String problem;
    private String date;
    private String time;
    private String location;
    private String status;
    private Object service;

    public MyBookingResponse(String _id, String problem, String date, String time, String location, String status, Object service) {
        this._id = _id;
        this.problem = problem;
        this.date = date;
        this.time = time;
        this.location = location;
        this.status = status;
        this.service = service;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }
}
