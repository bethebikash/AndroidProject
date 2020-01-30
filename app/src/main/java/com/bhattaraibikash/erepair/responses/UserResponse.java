package com.bhattaraibikash.erepair.responses;

public class UserResponse {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String username;
    private String image;

    public UserResponse(String name, String email, String address, String phone, String username, String image) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.image = image;
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }


    public String getUsername() {
        return username;
    }


    public String getImage() {
        return image;
    }
}
