package com.example.compose.unit5.showImageFromInternet.bookshelf.ui.screen

import BookShelfApiState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    bookshelfApiState: BookShelfApiState,
    retryAction: () -> Unit,
    contentPaddingValues: PaddingValues,
    ) {
    Text(text = "aaa")
}