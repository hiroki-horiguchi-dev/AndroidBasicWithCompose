import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.Amphibians.AmphibiansRepository
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.Amphibians.NetworkAmphibiansRepository
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.Amphibians.AmphibiansApiService
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.Amphibians.AmphibiansFavoriteApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/// この interface は、同じドメインの中で複数種類がある場合、それらを場合分けしてあげるのに適している
/// その都度、Repository と、Service クラスを作る必要はある
/// そのために、Repository, network パッケージの中で更にパッケージに分けると更にわかりやすいかな！
/// 試しに作ってみた
interface AmphibiansContainer {
    val amphibiansRepository: AmphibiansRepository
    val amphibiansFavoriteRepository: AmphibiansFavoriteRepository
}

class AmphibiansDefaultContainer : AmphibiansContainer {
    private val amphibiansBaseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"
    private val amphibiansRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(amphibiansBaseUrl)
        .build()

    /// 商品関連
    private val amphibiansRetrofitService: AmphibiansApiService by lazy {
        /// retrofit.create を見に行ったけど、結局　Handler をラップして使いやすくしてくれているんだよね
        amphibiansRetrofit.create(AmphibiansApiService::class.java)
    }

    /// 公開用商品関連 API の窓口
    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(amphibiansRetrofitService)
    }

    /// お気に入り関連
    private val amphibiansFavoriteRetrofitService: AmphibiansFavoriteApiService by lazy {
        amphibiansRetrofit.create(AmphibiansFavoriteApiService::class.java)
    }

    /// 公開用お気に入り関連 API の窓口
    override val amphibiansFavoriteRepository: AmphibiansFavoriteRepository by lazy {
        NetworkAmphibiansFavoriteRepository(amphibiansFavoriteApiService = amphibiansFavoriteRetrofitService)
    }

}