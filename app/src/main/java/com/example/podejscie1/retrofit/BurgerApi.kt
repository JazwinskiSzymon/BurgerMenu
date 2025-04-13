package com.example.podejscie1.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface BurgerApi {

    @GET("/burgers")
    suspend fun getBurgers(): Response<List<BurgerItem>>
}