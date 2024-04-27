import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookShelfRepository: BookShelfRepository
}

class BookShelfDefaultContainer() : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"
    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val bookShelfRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val bookshelfRetrofitService: BookShelfApiService by lazy {
        bookShelfRetrofit.create(BookShelfApiService::class.java)
    }

    override val bookShelfRepository: BookShelfRepository by lazy {
        NetworkBookShelfRepository(bookshelfRetrofitService)
    }
}