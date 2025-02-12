package com.example.usersecureapp.network;

import com.example.usersecureapp.models.UserResponse;
import com.example.usersecureapp.models.ImageResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // Fetch 10 random users from RandomUser.me API
    @GET("api/")
    Call<UserResponse> getUsers(@Query("results") int count);

    // Fetch images from Pixabay API (Now using BuildConfig for security)
    @GET("api/")
    Call<ImageResponse> getImages(
            @Query("key") String apiKey,  // API key stored in BuildConfig
            @Query("q") String query,     // Search query (e.g., "cats")
            @Query("image_type") String imageType
    );
}
