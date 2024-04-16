package com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface AnotherApiService {
    @GET("sample")
    suspend fun fetchAnotherApiData()
    @POST("sample/cart")
    suspend fun putAnotherApiData()
    @DELETE("sample/cart")
    suspend fun deleteAnotherApiData()
}