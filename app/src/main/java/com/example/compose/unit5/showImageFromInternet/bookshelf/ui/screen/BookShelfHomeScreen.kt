package com.example.compose.unit5.showImageFromInternet.bookshelf.ui.screen

import BookShelfApiState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose.R
import com.example.compose.unit5.showImageFromInternet.bookshelf.BookShelfApplication
import com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail.BookDetail
import com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail.ImageLinks

@Composable
fun BookShelfHomeScreen(
    bookshelfApiState: BookShelfApiState,
    retryAction: () -> Unit,
    contentPaddingValues: PaddingValues,
    modifier: Modifier = Modifier
    ) {
    when (bookshelfApiState) {
        is BookShelfApiState.Success -> {
            BookList(bookDetails = bookshelfApiState.bookDetail, contentPaddingValues = contentPaddingValues)
        }
        is BookShelfApiState.Loading -> {
            LoadingIndicator()
        }
        is BookShelfApiState.Error -> {
            ErrorScreen(message = bookshelfApiState.error.message ?: "Unknown error", onRetry = retryAction)
        }
        else -> {}
    }
}

@Composable
fun BookList(bookDetails: List<BookDetail>, contentPaddingValues: PaddingValues) {
    LazyColumn(
        contentPadding = contentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bookDetails) { it ->
            BookListItem(bookDetail = it)
        }
    }
}

@Composable
fun BookListItem(bookDetail: BookDetail , modifier: Modifier = Modifier) {
    Card {
        AsyncImage(
            modifier = modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(bookDetail.volumeInfo?.imageLinks?.thumbnail)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentDescription = bookDetail.volumeInfo?.title,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun LoadingIndicator() {
    // Display a loading indicator
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}