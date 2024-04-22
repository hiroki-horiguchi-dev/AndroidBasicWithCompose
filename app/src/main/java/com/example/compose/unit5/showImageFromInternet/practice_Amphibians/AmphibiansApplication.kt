package com.example.compose.unit5.showImageFromInternet.practice_Amphibians

import AmphibiansContainer
import AmphibiansDefaultContainer
import AnotherApiContainer
import AnotherApiDefaultContainer
import android.app.Application

class AmphibiansApplication: Application() {
    /// Amphibians の API 通信設定を UI Layer にグローバル変数で公開
    lateinit var amphibiansContainer: AmphibiansContainer
    /// 複数の Container を UI Layer に公開しようとすると、以下のエラーが出るので NG なのかも。。
    //  java.lang.RuntimeException: Unable to create application com.example.compose.unit5.showImageFromInternet.practice_Amphibians.AmphibiansApplication: java.lang.IllegalArgumentException: Expected URL scheme 'http' or 'https' but no scheme was found for
//    lateinit var anotherApiContainer: AnotherApiContainer

    override fun onCreate() {
        super.onCreate()
        amphibiansContainer = AmphibiansDefaultContainer()
//        anotherApiContainer = AnotherApiDefaultContainer()
    }
}
