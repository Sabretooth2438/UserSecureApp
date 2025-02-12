package com.example.usersecureapp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private Name name;

    @SerializedName("email")
    private String email;

    @SerializedName("picture")
    private Picture picture;

    public User() {} // Default constructor for deserialization

    public Name getName() { return name; }
    public String getEmail() { return email; }
    public Picture getPicture() { return picture; }

    public static class Name {
        @SerializedName("first")
        private String first;

        @SerializedName("last")
        private String last;

        public Name() {} // Default constructor

        public String getFirst() { return first; }
        public String getLast() { return last; }
        public String getFullName() {
            return first + " " + last;
        }
    }

    public static class Picture {
        @SerializedName("large")
        private String pictureUrl;

        public Picture() {} // Default constructor

        public String getPictureUrl() { return pictureUrl; }
    }
}
