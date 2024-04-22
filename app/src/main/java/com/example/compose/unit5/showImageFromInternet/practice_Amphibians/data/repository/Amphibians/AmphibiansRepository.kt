package com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.Amphibians

import Amphibians
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.Amphibians.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun fetchAmphibians(): List<Amphibians>
    suspend fun putNewAmphibians()
    suspend fun deleteAmphibians()
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun fetchAmphibians(): List<Amphibians> = amphibiansApiService.fetchAmphibians()
    override suspend fun putNewAmphibians() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAmphibians() {
        TODO("Not yet implemented")
    }
}
