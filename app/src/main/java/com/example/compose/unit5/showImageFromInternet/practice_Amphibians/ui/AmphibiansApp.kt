import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AmphibiansApp() {
    MaterialTheme {
        val viewModel: AmphibiansViewModel = viewModel(
            factory = AmphibiansViewModel.Factory
        )
        Text("aaa")
    }
}

@Composable
@Preview
fun PreviewAmphibiansApp() {
    AmphibiansApp()
}
