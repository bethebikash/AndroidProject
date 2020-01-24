package com.bhattaraibikash.erepair.models;

public class Service {
    private String _id;
    private String title;
    private String description;
    private String price;


    public Service(String _id, String title, String description, String price) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
