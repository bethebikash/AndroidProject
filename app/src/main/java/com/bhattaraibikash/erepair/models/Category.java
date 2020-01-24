package com.bhattaraibikash.erepair.models;

public class Category {
    private String _id;
    private String name;
    private String icon;

    public Category(String _id, String name, String icon) {
        this._id = _id;
        this.name = name;
        this.icon = icon;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
