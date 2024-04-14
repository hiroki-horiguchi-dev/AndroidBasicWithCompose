### ディレクトリ構成

![img.png](img.png)

### Retrofit

```kotlin
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Retrofit with Scalar Converter
implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

```

### sealed 修飾子について

```text
注: sealed キーワードを指定せずに MarsUiState interface を実装する場合は、
Success、Error、Loading、および else ブランチを追加する必要があります。
4 つ目のオプション（else）が存在しないため、sealed インターフェースを使用して、オプションが 3 つだけであることをコンパイラに知らせます
（これにより、条件が網羅的になります）。
```

![img_1.png](img_1.png)

#### kotlinx.serialization で JSON レスポンスを解析する

API モデルを作ってあげるやつ。

![img_2.png](img_2.png)

JSON レスポンスをアプリで使いやすい形に整形することをシリアル化解除、というらしい

kotlinx.serialization について

![img_3.png](img_3.png)

データクラス

![img_4.png](img_4.png)

```kotlin
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,  val img_src: String
)
```

@SerialName アノテーション
![img_5.png](img_5.png)


