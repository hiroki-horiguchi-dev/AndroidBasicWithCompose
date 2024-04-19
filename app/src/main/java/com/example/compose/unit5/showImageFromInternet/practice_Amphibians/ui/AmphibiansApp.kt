import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.ui.theme.WoofTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    /// 下にスクロールすると AppBar を折りたたむ設定
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Amphibians",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        }
    ) {
        val viewModel: AmphibiansViewModel = viewModel(
            factory = AmphibiansViewModel.Factory
        )
        HomeScreen(
            amphibiansApiState = viewModel.amphibiansApiState,
            retryAction = {},
            contentPaddingValues = it
        )
    }
}

@Composable
@Preview
fun PreviewAmphibiansApp() {
    WoofTheme {
        AmphibiansApp()
    }
}
