package com.example.usersecureapp.models;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("id")
    private int id;

    @SerializedName("webformatURL")
    private String imageUrl;

    @SerializedName("user")
    private String photographer;

    @SerializedName("tags")
    private String tags;  // New field for extra data

    public Image() {} // Default constructor

    public int getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public String getPhotographer() { return photographer; }
    public String getTags() { return tags; }
}
