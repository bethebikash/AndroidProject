package com.bhattaraibikash.erepair.responses;

public class UserResponse {
    private String _id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String username;
    private String image;

    public UserResponse(String _id, String name, String email, String address, String phone, String username, String image) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.image = image;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
