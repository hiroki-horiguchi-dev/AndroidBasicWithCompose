import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    viewModel: AmphibiansViewModel,
    amphibiansUiState: AmphibiansApiState,
    contentPaddingValues: PaddingValues
) {
    Text(text = amphibiansUiState.toString())
}

