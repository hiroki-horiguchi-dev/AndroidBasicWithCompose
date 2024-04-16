package com.example.compose.unit5.showImageFromInternet.repository_di.ui.screens.fake

import com.example.compose.unit5.showImageFromInternet.repository_di.model.MarsPhoto

object FakeDataSource {
    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "url.1"
    const val imgTwo = "url.2"
    val photosList = listOf(
        MarsPhoto(
            id = idOne,
            imgSrc = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            imgSrc = imgTwo
        )
    )
}