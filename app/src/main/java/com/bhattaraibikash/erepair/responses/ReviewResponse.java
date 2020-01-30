package com.bhattaraibikash.erepair.responses;

public class ReviewResponse {
    private String _id;
    private Object user;
    private String createdAt;
    private String review;

    public ReviewResponse(String _id, Object user, String createdAt, String review) {
        this._id = _id;
        this.user = user;
        this.createdAt = createdAt;
        this.review = review;
    }

    public String get_id() {
        return _id;
    }

    public Object getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getReview() {
        return review;
    }
}
