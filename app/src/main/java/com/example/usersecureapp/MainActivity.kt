package com.example.usersecureapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usersecureapp.models.User
import com.example.usersecureapp.models.UserResponse
import com.example.usersecureapp.models.Image
import com.example.usersecureapp.models.ImageResponse
import com.example.usersecureapp.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private val apiRepository = ApiRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppUI()
        }
    }

    @Composable
    fun AppUI() {
        var userList by remember { mutableStateOf(emptyList<User>()) }
        var imageList by remember { mutableStateOf(emptyList<Image>()) }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            fetchUsers { users, error ->
                if (error != null) errorMessage = error else userList = users
                isLoading = false
            }
            fetchImages("nature") { images, error ->
                if (error != null) errorMessage = error else imageList = images
                isLoading = false
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                errorMessage?.let {
                    Text("Error: $it", color = MaterialTheme.colorScheme.error)
                }

                Text("Users:")
                userList.forEach { user ->
                    Text("${user.name.fullName} - ${user.email}")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Images:")
                imageList.forEach { image ->
                    Text("Image URL: ${image.imageUrl}")
                }
            }
        }
    }

    private fun fetchUsers(onResult: (List<User>, String?) -> Unit) {
        apiRepository.fetchUsers(10, object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    onResult(response.body()!!.users, null)
                } else {
                    onResult(emptyList(), "Error ${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onResult(emptyList(), "Failed to fetch users: ${t.message}")
            }
        })
    }

    private fun fetchImages(query: String, onResult: (List<Image>, String?) -> Unit) {
        apiRepository.fetchImages(query, object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    onResult(response.body()!!.images, null)
                } else {
                    onResult(emptyList(), "Error ${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                onResult(emptyList(), "Failed to fetch images: ${t.message}")
            }
        })
    }
}
