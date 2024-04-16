package com.example.compose.unit5.showImageFromInternet.repository_di.ui.data

import com.example.compose.unit5.showImageFromInternet.repository_di.model.MarsPhoto
import com.example.compose.unit5.showImageFromInternet.repository_di.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}