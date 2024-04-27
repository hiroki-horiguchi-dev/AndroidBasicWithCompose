import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.compose.unit5.showImageFromInternet.bookshelf.BookShelfApplication
import com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail.BookDetail
import com.example.compose.unit5.showImageFromInternet.bookshelf.model.book_detail.ImageLinks
import com.example.compose.unit5.showImageFromInternet.bookshelf.model.books.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


sealed interface BookShelfApiState {
    data class Success(val bookDetail: List<BookDetail>) : BookShelfApiState
    object Loading : BookShelfApiState
    data class Error(val error: Exception) : BookShelfApiState
}

class BookShelfViewModel(
    private val bookShelfRepository: BookShelfRepository
) : ViewModel() {

    init {
        fetchBookShelf()
    }

    var bookshelfApiState: BookShelfApiState by mutableStateOf(BookShelfApiState.Loading)
        private set

    /// 解法1
    fun fetchBookShelf() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                bookShelfRepository.fetchBooks("jazz+history").items
            }.fold(
                onSuccess = { bookList ->
                    if (bookList.isNullOrEmpty()) {
                        bookshelfApiState = BookShelfApiState.Error(Exception("fetch books failed"))
                        return@launch
                    }
                    fetchBookDetail(bookList)
                },
                onFailure = {
                    bookshelfApiState = BookShelfApiState.Error(Exception("fetch books failed"))
                    return@launch
                }
            )
        }
    }

    private fun fetchBookDetail(bookList: List<Item>) = viewModelScope.launch {
        val bookDetailList = mutableListOf<BookDetail>()
        for (book in bookList) {
            runCatching {
                bookShelfRepository.fetchBookDetail(
                    book.id
                )
            }.onSuccess {
                bookDetailList.add(it)
                Log.d(TAG, "fetchBookDetail: $it")

            }.onFailure {
                Log.d(TAG, "fetchBookDetail: $it")
            }
        }
        bookshelfApiState = BookShelfApiState.Success(bookDetailList)
    }

    companion object {
        private val TAG = BookShelfViewModel::class.java.simpleName

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