import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class BookShelfViewModel(
    private val bookShelfRepository: BookShelfRepository
): ViewModel() {



    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val bookShelfRepository = application.bookShelfDefaultContainer.bookShelfRepository
                BookShelfViewModel(
                    bookShelfRepository = bookShelfRepository
                )
            }
        }
    }

}