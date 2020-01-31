package com.bhattaraibikash.erepair.models;

public class Request {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String skills;

    public Request(String name, String email, String address, String phone, String skills) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
