package com.example.routes.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Rede {
    companion object{
        fun getRetrofitInstace(url: String): Retrofit{
            return Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}