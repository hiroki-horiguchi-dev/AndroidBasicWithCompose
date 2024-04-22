import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.AmphibiansApplication
import com.example.compose.unit5.showImageFromInternet.practice_Amphibians.data.repository.Amphibians.AmphibiansRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface AmphibiansApiState {
    data class Success(val amphibians: List<Amphibians>) : AmphibiansApiState
    object Error : AmphibiansApiState
    object Loading : AmphibiansApiState
}

class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository,
    private val amphibiansFavoriteRepository: AmphibiansFavoriteRepository
) : ViewModel() {

    var amphibiansApiState: AmphibiansApiState by mutableStateOf(AmphibiansApiState.Loading)
        private set

    init {
        fetchAmphibiants()
    }

    fun fetchAmphibiants() {
        viewModelScope.launch {
            amphibiansApiState = try {
                val result = amphibiansRepository.fetchAmphibians()
                Log.d("AmphibiansViewModel", result.toString())
                AmphibiansApiState.Success(result)
            } catch (e: IOException) {
                AmphibiansApiState.Error
            } catch (e: HttpException) {
                AmphibiansApiState.Error
            }
        }
    }

    /// Example. お気に入り登録処理
    fun registerFavorite(id: String) {
        viewModelScope.launch {
            try {
                val result = amphibiansFavoriteRepository.registerFavorite(id)
            } catch (e: IOException) {
                println(e.toString())
            } catch (e: HttpException) {
                println(e.toString())
            }
        }
    }

    /// Example. お気に入り削除処理
    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            try {
                val result = amphibiansFavoriteRepository.deleteFavorite(id)
            } catch (e: IOException) {
                println(e.toString())
            } catch (e: HttpException) {
                println(e.toString())
            }
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.amphibiansContainer.amphibiansRepository
                // こんな感じでお気に入りAPIがあった場合も ViewModel に渡せるね
                val amphibiansFavoriteRepository =
                    application.amphibiansContainer.amphibiansFavoriteRepository
                AmphibiansViewModel(
                    amphibiansRepository = amphibiansRepository,
                    amphibiansFavoriteRepository = amphibiansFavoriteRepository
                )
            }
        }
    }

}