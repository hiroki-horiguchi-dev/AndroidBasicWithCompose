import Datasource.dessertList
import androidx.annotation.DrawableRes

data class ArchitectureComponentPracticeState(
    val revenue: Int = 0,
    val dessertSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)