import com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail.BookDetail


interface BookShelfRepository {
    suspend fun fetchBooks(query: String): BookShelf
    suspend fun fetchBookDetail(volumeId: String): BookDetail
}


class NetworkBookShelfRepository(
    private val bookShelfApiService: BookShelfApiService
): BookShelfRepository {
    override suspend fun fetchBooks(query: String): BookShelf = bookShelfApiService.fetchBooks(query = query)
    override suspend fun fetchBookDetail(volumeId: String): BookDetail = bookShelfApiService.fetchBookDetail(volumeId = volumeId)
}