package com.example.usersecureapp.repository;

import com.example.usersecureapp.models.UserResponse;
import com.example.usersecureapp.models.User;
import com.example.usersecureapp.models.ImageResponse;
import com.example.usersecureapp.models.Image;
import com.example.usersecureapp.network.ApiService;
import com.example.usersecureapp.network.RetrofitClient;
import com.example.usersecureapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class ApiRepository {
    private final ApiService apiService;
    private static final String API_KEY = BuildConfig.PIXABAY_API_KEY; // Secure API key access

    public ApiRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void fetchUsers(int count, Callback<UserResponse> callback) {
        Call<UserResponse> call = apiService.getUsers(count);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("ApiRepository", "Error fetching users: " + response.code() + " - " + response.message());
                    callback.onFailure(call, new Throwable("Error " + response.code() + ": " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("ApiRepository", "Failed to fetch users", t);
                callback.onFailure(call, t);
            }
        });
    }

    public void fetchImages(String query, Callback<ImageResponse> callback) {
        Call<ImageResponse> call = apiService.getImages(API_KEY, query, "photo");
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("ApiRepository", "Error fetching images: " + response.code() + " - " + response.message());
                    callback.onFailure(call, new Throwable("Error " + response.code() + ": " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.e("ApiRepository", "Failed to fetch images", t);
                callback.onFailure(call, t);
            }
        });
    }
}
