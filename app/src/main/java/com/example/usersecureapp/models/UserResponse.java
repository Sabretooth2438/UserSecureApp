package com.example.usersecureapp.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserResponse {
    @SerializedName("results")
    private List<User> users;

    public UserResponse() {} // Default constructor

    public List<User> getUsers() {
        return users;
    }
}
