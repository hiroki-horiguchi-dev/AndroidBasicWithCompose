package com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository

import Amphibians
import AmphibiansApiService

/// ぶっちゃけ、ここで interface を定義するメリットは API の数(AmphibiansApiService で定義する API 通信に用いる処理の数)が少ないなら無いと思う
/// が、API のメソッドが増えれば増えるほど、当たり前に必要になってくるものでもある
interface AmphibiansRepository {
    suspend fun fetchAmphibians(): List<Amphibians>
    suspend fun putNewAmphibians()
    suspend fun deleteAmphibians()
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun fetchAmphibians(): List<Amphibians> = amphibiansApiService.fetchAmphibians()
    override suspend fun putNewAmphibians() {
        TODO("Example. @PUT")
    }
    override suspend fun deleteAmphibians() {
        TODO("Example. @DELETE")
    }
}
