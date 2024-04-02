import Datasource.dessertList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArchitectureComponentPracticeViewModel: ViewModel() {
    /// state
    private val _uiState = MutableStateFlow(ArchitectureComponentPracticeState())
    val uiState: StateFlow<ArchitectureComponentPracticeState> = _uiState.asStateFlow()

    init {
        val firstDessert = dessertList.first()
        _uiState.update {currentState ->
            currentState.copy(
                dessertSold = 0,
                currentDessertPrice = firstDessert.price,
                currentDessertImageId = firstDessert.imageId,
                currentDessertIndex = dessertList.indexOf(firstDessert)
            )
        }
    }

    fun updateRevenue() {
        _uiState.update {currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertSold = currentState.dessertSold + 1
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    fun determinDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ) {
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                updateImageAndPrice(dessert)
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
    }

    private fun updateImageAndPrice(dessert: Dessert) {
        _uiState.update {currentState ->
            currentState.copy(
                currentDessertImageId = dessert.imageId,
                currentDessertPrice = dessert.price
            )
        }
    }

}