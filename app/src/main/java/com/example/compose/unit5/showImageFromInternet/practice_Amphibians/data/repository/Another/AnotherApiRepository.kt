package com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.Another

import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.Another.AnotherApiService

interface AnotherApiRepository {
    suspend fun fetchAnotherApiData()
    suspend fun putAnotherApiData()
    suspend fun deleteAnotherApiData()
}

class NetworkAnotherApiRepository(
    private val anotherApiService: AnotherApiService
): AnotherApiRepository {
    override suspend fun fetchAnotherApiData() {
        TODO("Not yet implemented")
    }

    override suspend fun putAnotherApiData() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAnotherApiData() {
        TODO("Not yet implemented")
    }
}
