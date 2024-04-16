package com.example.compose.unit5.showImageFromInternet.repository_di

import android.app.Application
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.data.AppContainer
import com.example.compose.unit5.showImageFromInternet.repository_di.ui.data.DefaultAppContainer

/// ここで Application クラスを作成
/// アプリケーション全体に data layer への窓口を公開する役割をしてもらう
class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}