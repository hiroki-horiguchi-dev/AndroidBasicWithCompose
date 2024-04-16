import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.AnotherApiRepository
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.NetworkAnotherApiRepository
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.AnotherApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AnotherApiContainer{
     val anotherApiRepository: AnotherApiRepository
}

class AnotherApiDefaultContainer: AnotherApiContainer {
    private val anotherAPiBaseUrl = ""
    private val anotherApiRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(anotherAPiBaseUrl)
        .build()

    private val anotherApiRetrofitService: AnotherApiService by lazy {
        anotherApiRetrofit.create(AnotherApiService::class.java)
    }

    override val anotherApiRepository: NetworkAnotherApiRepository by lazy {
        NetworkAnotherApiRepository(anotherApiRetrofitService)
    }
}