import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.compose.ui.theme.WoofTheme

class AmphibiansActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
//                    com.example.compose.unit5.showImageFromInternet.bookshelf.ui.screen.HomeScreen()
                }
            }
        }
    }
}