package com.example.usersecureapp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
