import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.WoofTheme

@Composable
@Preview(showBackground = true)
fun PreviewWoofTheme() {
    WoofTheme(darkTheme = false) {
        WoofApp(dogs)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDogCard() {
    DogCard(dog = dogs[0])
}

@Composable
fun WoofApp(dogs: List<Dog>) {
    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dogs) {
            DogCard(it)
        }
    }
}

@Composable
fun DogCard(dog: Dog) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
    ) {
        Image(
            painter = painterResource(id = dog.imageResourceId), 
            contentDescription = stringResource(id = dog.name),
            modifier = Modifier.size(width = 46.dp, height = 46.dp)
        )
        Column {
            Text(text = stringResource(id = dog.name))
            Text(text = stringResource(id = dog.age))
        }
    }
}
