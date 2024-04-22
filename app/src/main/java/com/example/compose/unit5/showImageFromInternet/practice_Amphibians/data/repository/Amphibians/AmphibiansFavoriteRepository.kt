import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.Amphibians.AmphibiansFavoriteApiService

interface AmphibiansFavoriteRepository {
    suspend fun registerFavorite(id: String)
    suspend fun deleteFavorite(id: String)
}


class NetworkAmphibiansFavoriteRepository(
    private val amphibiansFavoriteApiService: AmphibiansFavoriteApiService
): AmphibiansFavoriteRepository {
    override suspend fun registerFavorite(id: String) {
        amphibiansFavoriteApiService.registerFavorite(id)
    }


    override suspend fun deleteFavorite(id: String) {
        amphibiansFavoriteApiService.deleteFavorite(id)
    }
}