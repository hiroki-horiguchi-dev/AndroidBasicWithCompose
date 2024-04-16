import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun fetchAmphibians(): List<Amphibians>
}