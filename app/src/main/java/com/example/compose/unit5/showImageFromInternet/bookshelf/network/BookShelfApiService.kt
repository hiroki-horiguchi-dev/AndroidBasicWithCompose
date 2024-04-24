import com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail.BookDetail
import com.example.compose.unit5.showImageFromInternet.bookshelf.model.books.BookShelf
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookShelfApiService {

    @GET("volumes")
    suspend fun fetchBooks(@Query("q") query: String): BookShelf

    @GET("volumes/{volume_id}")
    suspend fun fetchBookDetail(@Path("volume_id") volumeId: String): BookDetail
    
}