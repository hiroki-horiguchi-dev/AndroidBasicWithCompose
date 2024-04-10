### 様々な画面サイズに対応する

これを求められたプロジェクトがあんまりないからパス。
動画は見た。

https://developer.android.com/courses/pathways/android-basics-compose-unit-4-pathway-3?hl=ja

![img.png](img.png)

大画面用に設計しようねって話し。

![img_1.png](img_1.png)

どうやってやるのか？
動画で話していたように activity から画面サイズを取得して条件分岐するよ。

![img_2.png](img_2.png)

### 導入方法

```kotlin
...
dependencies {
...
"androidx.compose.material3:material3-window-size-class:$material3_version"
...
```

![img_3.png](img_3.png)

```kotlin
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
        

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
        ReplyTheme {
            val windowSize = calculateWindowSizeClass(this)
            ReplyApp()
        }
    }
}
```

![img_4.png](img_4.png)

```text
このエラー メッセージが表示される理由は、現在 material3-window-size-class API が、このアノテーションを必要とするアルファ版バージョンであるためです。
```

![img_5.png](img_5.png)

```kotlin
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {}
```

![img_6.png](img_6.png)


```kotlin

         setContent {
            ReplyTheme {
                val windowSize = calculateWindowSizeClass(this)
                ReplyApp(
                    windowSize = windowSize.widthSizeClass
                )
            }
         }
```

![img_7.png](img_7.png)

```kotlin
@Preview(showBackground = true)
@Composable
fun ReplyAppPreview() {
    ReplyTheme {
        ReplyApp(
            windowSize = WindowWidthSizeClass.Compact,
        )
    }
}
```

![img_8.png](img_8.png)

```kotlin
@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
        }
        WindowWidthSizeClass.Medium -> {
        }
        WindowWidthSizeClass.Expanded -> {
        }
        else -> {
        }
    }
}
```

### アダプティブ ナビゲーション レイアウトを実装する

こっちは後ででいいや。とりあえず、Composable 関数に画面サイズを渡してそれ相応の UI を出そうねって話だけインデックスできていればいい。