import android.app.Application

class BookShelfApplication: Application() {
    lateinit var bookShelfDefaultContainer: AppContainer


    override fun onCreate() {
        super.onCreate()
        bookShelfDefaultContainer = BookShelfDefaultContainer()
    }

}

