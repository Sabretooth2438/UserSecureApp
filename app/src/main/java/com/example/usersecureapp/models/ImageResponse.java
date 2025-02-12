package com.example.usersecureapp.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ImageResponse {
    @SerializedName("hits")
    private List<Image> images;

    public ImageResponse() {} // Default constructor

    public List<Image> getImages() {
        return images;
    }
}
