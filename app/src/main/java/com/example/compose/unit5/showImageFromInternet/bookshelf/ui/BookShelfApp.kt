package com.example.compose.unit5.showImageFromInternet.bookshelf.ui

import BookShelfViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.ui.theme.WoofTheme
import com.example.compose.unit5.showImageFromInternet.bookshelf.ui.screen.BookShelfHomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfApp(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Book Shelf",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) {
        val viewModel: BookShelfViewModel = viewModel(
            factory = BookShelfViewModel.Factory
        )
        BookShelfHomeScreen(
            viewModel.bookshelfApiState,
            retryAction = viewModel::fetchBookShelf,
            contentPaddingValues = it,
            modifier
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewBookShelfApp() {
    WoofTheme {
        BookShelfApp()
    }
}