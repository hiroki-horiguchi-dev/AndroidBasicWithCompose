import kotlinx.serialization.Serializable

@Serializable
data class BookShelf(
    val kind: String,
    val totalItem: Int,
    val items: List<Items>
)

@Serializable
data class Items (
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
    val accessInfo: AccessInfo,
    val searchInfo: SearchInfo
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

@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean
)

@Serializable
data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: Epub,
    val pdf: Pdf,
    val webReaderLink: String,
    val accessViewStatus: String,
    val quoteSharingAllowed: Boolean
)

@Serializable
data class Epub(
    val isAvailable: Boolean
)

@Serializable
data class Pdf(
    val isAvailable: Boolean
)

@Serializable
data class SearchInfo(
    val textSnippet: String
)