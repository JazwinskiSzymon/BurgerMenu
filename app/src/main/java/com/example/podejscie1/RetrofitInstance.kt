package com.example.podejscie1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: BurgerApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-burger-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BurgerApi::class.java)
    }
}