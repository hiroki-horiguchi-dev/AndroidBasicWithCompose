package com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens

import com.example.compose.unit5.showImageFromInternet.repository_di.ui.data.NetworkMarsPhotosRepository
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake.FakeDataSource
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake.FakeMarsApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

/// リポジトリのテスト
class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }

}