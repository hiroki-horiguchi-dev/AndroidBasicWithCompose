![img.png](img.png)
![img_1.png](img_1.png)

完成物イメージ

![img_2.png](img_2.png)
タイポグラフィ

### マテリアルテーマ設定と色の追加

![img_3.png](img_3.png)

[マテリアルテーマビルダー](https://m3.material.io/theme-builder#/custom)の Primary で primaryColor を設定すると、

![img_4.png](img_4.png)

こんな感じでいい感じのカラー配置をしてくれる。

![img_5.png](img_5.png)

それぞれの役割の説明と活用方法↑

![img_6.png](img_6.png)

アプリへの導入方法

### シェイプを追加する

```kotlin
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(50.dp),
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp)
)
```
こんな感じで定義

![img_7.png](img_7.png)
こういうこともできる

![img_8.png](img_8.png)
カードコンポーネントはサーフェスとして一つのコンポーザブルを含めることができて、修飾オプションも用意されているんだってさ
枠線、シェイプなどを追加できる。

Row, Column, LazyColumn, LazyRow, Text, Image あたりは固有の役割がわかりやすかった。
だから、Card とか Surface とかの存在意義が謎だったが、要するに修飾するのに便利だよねって話なんだねと。

### タイプグラフィを追加する

![img_9.png](img_9.png)

フォント初期化
別にこれは最後でいいや、もっと重要なことがたくさんある

### トップバーを追加する

Scaffold で topBar を追加できる、Flutter と似ている。
呼び出しもと
```kotlin
    Scaffold(
        topBar = { WoofTopAppBar() }
    ) 
```

実装
```kotlin
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    // Font で設定したフォントになって表示されるよ、一旦飛ばしたので適応されていないよ
                    text = "woof",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}
```