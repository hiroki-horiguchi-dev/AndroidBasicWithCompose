import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.AmphibiansRepository
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.NetworkAmphibiansRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
interface AmphibiansContainer {
    val amphibiansRepository: AmphibiansRepository
}

class AmphibiansDefaultContainer : AmphibiansContainer {
    private val amphibiansBaseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"
    private val amphibiansRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(amphibiansBaseUrl)
        .build()

    private val amphibiansRetrofitService: AmphibiansApiService by lazy {
        /// retrofit.create を見に行ったけど、結局　Handler をラップして使いやすくしてくれているんだよね
        amphibiansRetrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(amphibiansRetrofitService)
    }
}