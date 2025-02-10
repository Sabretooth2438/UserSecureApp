package com.example.usersecureapp.models;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("id")
    private int id;

    @SerializedName("url")
    private String imageUrl;

    public int getId() { return id; }
    public String getImageUrl() { return imageUrl; }
}
