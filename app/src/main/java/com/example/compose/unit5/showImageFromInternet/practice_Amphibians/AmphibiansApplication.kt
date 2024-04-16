import android.app.Application

class AmphibiansApplication: Application() {
    /// Amphibians の API 通信設定を UI Layer にグローバル変数で公開
    lateinit var amphibiansContainer: AmphibiansContainer
    /// Another API の API 通信設定を UI Layer にグローバル変数で公開
    lateinit var anotherApiContainer: AnotherApiContainer

    override fun onCreate() {
        super.onCreate()
        amphibiansContainer = AmphibiansDefaultContainer()
        anotherApiContainer = AnotherApiDefaultContainer()
    }
}
