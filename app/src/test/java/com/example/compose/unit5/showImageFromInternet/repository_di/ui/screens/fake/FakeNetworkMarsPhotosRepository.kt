package com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake

import com.example.compose.unit5.showImageFromInternet.repository_di.model.MarsPhoto
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.data.MarsPhotosRepository

class FakeNetworkMarsPhotosRepository: MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}