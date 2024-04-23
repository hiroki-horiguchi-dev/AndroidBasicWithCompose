package com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetail(
    val kind: String,
    val id: String,
    @SerialName("etag")
    val eTag: String,
    val setLink: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String = "",
    val thumbnail: String = "",
    val small: String = "",
    val medium: String = ""
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val description: String,
    val industryIdentifiers: List<IndustryIdentifier>,
    val readingModes: ReadingModes,
    val printType: String,
    val categories: List<String>,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: PanelizationSummary,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String
)

@Serializable
data class IndustryIdentifier(
    val type: String,
    val identifier: String
)

@Serializable
data class ReadingModes(
    val text: Boolean,
    val image: Boolean
)

@Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)