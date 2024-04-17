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


class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository,
    private val amphibiansFavoriteRepository: AmphibiansFavoriteRepository
): ViewModel() {

    init {
        fetchAmphibiants()
    }

    fun fetchAmphibiants() {
        viewModelScope.launch {
            try {
                val result = amphibiansRepository.fetchAmphibians()
                println("list is here: $result")
            } catch (e: IOException) {
                println(e.toString())
            } catch (e: HttpException) {
                println(e.toString())
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
                val amphibiansFavoriteRepository = application.amphibiansContainer.amphibiansFavoriteRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository, amphibiansFavoriteRepository = amphibiansFavoriteRepository)
            }
        }
    }

}