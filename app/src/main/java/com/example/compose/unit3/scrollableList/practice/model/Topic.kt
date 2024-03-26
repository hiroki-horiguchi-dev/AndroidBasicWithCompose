import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResource: Int,
    val favorite: Int,
    @DrawableRes val drawableRes: Int
)