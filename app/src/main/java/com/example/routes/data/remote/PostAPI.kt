package com.example.routes.data.remote

import com.example.routes.data.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPI {

    @GET("posts/{id}")
    fun getPostInfo(@Path("id") id: Int): Call<Post>
}