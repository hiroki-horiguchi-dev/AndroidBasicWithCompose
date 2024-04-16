package com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens

import com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake.FakeDataSource
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake.FakeNetworkMarsPhotosRepository
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest{
            /// ViewModel に FakeRepository を与えると、init で FakeRepository.getPhotos() を呼んでくれる
            /// FakeRepository の getPhotos() は FakeDataSource.photoList を返す
            /// なので assertEquals の expected は FakeDataSource.photoList のサイズと同じはずなので、
            /// expected が以下のようになるよ
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
            assertEquals(
                MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars " +
                        "photos retrieved"),
                marsViewModel.marsUiState
            )
        }
}