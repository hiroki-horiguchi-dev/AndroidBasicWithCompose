package com.example.compose.unit5.showImageFromInternet.practice_Amphibians.network.Amphibians

import retrofit2.http.Field
import retrofit2.http.POST

interface AmphibiansFavoriteApiService {
    @POST("/amphibians/put")
    suspend fun registerFavorite(
        @Field("unique_id") id: String
    )

    @POST("/amphibians/delete")
    suspend fun deleteFavorite(
        @Field("unique_id") id: String
    )
}