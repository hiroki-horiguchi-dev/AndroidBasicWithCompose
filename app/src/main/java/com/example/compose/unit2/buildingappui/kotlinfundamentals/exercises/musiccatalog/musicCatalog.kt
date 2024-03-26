package com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.musiccatalog

/// 面倒なのでファイル分けません
/// モデルを想定
class Song(
    val title: String,
    val artist: String,
    val releaseYear: Int,
    val numberOfTimesPlayed: Int
) {

    fun isPopular(): Boolean {
        return numberOfTimesPlayed > 999
    }

    fun description(): String{
        return "$title, performed by $artist, was released in $releaseYear"
    }

    fun printDescription(msg: String) {
        println(msg)
    }
}