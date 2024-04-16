package com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake

import com.example.compose.unit5.showImageFromInternet.repository_di.model.MarsPhoto
import com.example.compose.unit5.showImageFromInternet.repository_di.network.MarsApiService

class FakeMarsApiService: MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        /// Fake を返す FakeApiService
        return FakeDataSource.photosList
    }
}