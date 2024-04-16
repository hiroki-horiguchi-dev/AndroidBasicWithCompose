package com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network

interface AnotherApiService {
    suspend fun fetchAnotherApiData()
    suspend fun putAnotherApiData()
    suspend fun deleteAnotherApiData()
}