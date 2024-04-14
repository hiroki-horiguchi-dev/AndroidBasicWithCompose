import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,
    /// Json レスポンスと同名の変数名を宣言することで、モデルを API レスポンス返却時に指定してあげるといい感じにやってくれる。
    /// ただし、Json レスポンスの名称がモデルで宣言する変数名として不適切な場合、
    /// 以下のようにすれば、アプリで使う変数名と Json レスポンスのプロパティ名を置き換えることができる。
    @SerialName(value = "img_src")
    val imgSrc: String
)
