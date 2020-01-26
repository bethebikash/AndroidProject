package com.bhattaraibikash.erepair.models;

public class ReviewResponse {
    private String _id;
    private String user;
    private String address;
    private String dateOfReview;
    private String review;

    public ReviewResponse(String _id, String user, String address, String dateOfReview, String review) {
        this._id = _id;
        this.user = user;
        this.address = address;
        this.dateOfReview = dateOfReview;
        this.review = review;
    }


    public String getUser() {
        return user;
    }


    public String getAddress() {
        return address;
    }


    public String getDateOfReview() {
        return dateOfReview;
    }


    public String getReview() {
        return review;
    }

}
