package com.example.dz16retrofitpost.domain.mod
import com.example.dz16retrofitpost.domain.models.ApiData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("woof.json")
    suspend fun getRandomDog() : Response<ApiData>
}