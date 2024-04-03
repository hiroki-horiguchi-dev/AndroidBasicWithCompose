import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/// フレーバー選択画面
@Composable
fun SelectFlavorScreen(
    subTotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit,
    modifier: Modifier = Modifier) {
}

@Composable
@Preview
fun PreviewSelectFlavorScreen() {
//    SelectFlavorScreen(0)
}