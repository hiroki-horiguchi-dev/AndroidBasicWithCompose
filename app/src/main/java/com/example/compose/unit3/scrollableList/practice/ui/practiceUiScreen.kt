import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/// 回答コードとほぼ同じ。aspectRatio を使っていないのが NG かな。。
@Preview(showBackground = true)
@Composable
fun previewPracticeCardScreen() {
    GridCard(TopicDataSource.topics[0])
}

@Preview(showBackground = true)
@Composable
fun previewPracticeCardsScreen() {
    PracticeScreen(TopicDataSource.topics)
}

@Preview("Box リファレンス")
@Composable
fun previewBox() {
//    BoxReference()
}

@Preview("aspectRatio リファレンス")
@Composable
fun previewAspectRatio() {
//    AspectRatioReference()
}

@Composable
fun PracticeScreen(topics: List<Topic>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(topics) { topic ->
                GridCard(data = topic)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 8.dp)
    )
}

@Composable
fun GridCard(data: Topic, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation()
    ) {
        Row {
            Image(
                painter = painterResource(id = data.drawableRes),
                contentDescription = stringResource(id = data.stringResource),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp)
                    .aspectRatio(1f)
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = data.stringResource),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        Icons.Rounded.Share,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = data.favorite.toString(), modifier = Modifier.padding(
                            start = 8.dp
                        ), style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun BoxReference() {
    Box {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Cyan)
        )
        Box(
            Modifier
                .matchParentSize()
                .padding(top = 20.dp, bottom = 20.dp)
                .background(Color.Yellow)
        )
        Box(
            Modifier
                .matchParentSize()
                .padding(40.dp)
                .background(Color.Magenta)
        )
        Box(
            Modifier
                .align(Alignment.Center)
                .size(300.dp, 300.dp)
                .background(Color.Green)
        )
        Box(
            Modifier
                .align(Alignment.TopStart)
                .size(150.dp, 150.dp)
                .background(Color.Red)
        )
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .size(150.dp, 150.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
fun AspectRatioReference() {
    Box(
        Modifier
            .width(100.dp)
            .aspectRatio(2f)
            .background(Color.Green)
    )
}

