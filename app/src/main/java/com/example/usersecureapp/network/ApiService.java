package com.example.usersecureapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;
import com.example.usersecureapp.models.User;
import com.example.usersecureapp.models.Image;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers(); // Fetches user list

    @GET("images")
    Call<List<Image>> getImages(@Query("page") int page, @Query("limit") int limit); // Fetch images with pagination
}
