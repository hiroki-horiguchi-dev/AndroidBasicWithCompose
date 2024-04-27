package com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail

import kotlinx.serialization.Serializable

@Serializable
data class BookDetail(
    val id: String,
    val volumeInfo: VolumeInfo?
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null,
    val small: String? = null,
    val medium: String? = null
)