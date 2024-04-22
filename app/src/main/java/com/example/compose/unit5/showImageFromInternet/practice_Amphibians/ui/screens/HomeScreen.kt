import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose.R

@Composable
fun HomeScreen(
    amphibiansApiState: AmphibiansApiState,
    retryAction: () -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    when (amphibiansApiState) {
        is AmphibiansApiState.Success -> AmphibiansCardScreen(
            amphibian = amphibiansApiState.amphibians[0], modifier = modifier.fillMaxSize()
        )
        is AmphibiansApiState.Loading -> LoadingScreen()
        else -> ErrorScreen()
    }
}

@Composable
private fun AmphibiansListScreen(amphibiansList: List<Amphibians>, modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
private fun LoadingScreen() {
}

@Composable
private fun ErrorScreen() {
}

@Composable
private fun AmphibiansCardScreen(amphibian: Amphibians, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.amphibian_title, amphibian.name, amphibian.type),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imsSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Fit,
            )

            Text(
                text = amphibian.description,
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewAmphibiansCardScreen() {
    val dummyAmphibians = Amphibians(
        name = "Great Basin Spadefoot (Toad)",
        type = "Frog",
        description = "This toad spends most of its life underground due to the arid desert conditions ...",
        imsSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/roraima-bush-toad.png"

    )
    AmphibiansCardScreen(
        amphibian = dummyAmphibians, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.padding_medium),
                top = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium)
            )
    )
}