import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

/// ここには Amphibians(https://android-kotlin-fun-mars-server.appspot.com) のドメインを使う API 通信のメソッドを定義する
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun fetchAmphibians(): List<Amphibians>

    /// 例えば Post もあって然るべきだよね
    @PUT("sample1")
    suspend fun putNewAmphibians(
        @Header("unique_id") uniqueId: String,
        @Field("sample_id") sampleId: String
    )

    @DELETE
    suspend fun deleteAmphibians(
        @Header("unique_id") uniqueId: String,
        @Field("sample_id") sampleId: String
    )
}