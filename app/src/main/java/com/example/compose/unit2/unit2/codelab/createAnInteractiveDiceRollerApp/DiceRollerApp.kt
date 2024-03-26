package com.example.compose.unit2.buildingappui.addabuttontoapp.codelab.createAnInteractiveDiceRollerApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
// runtime の getValue, setValue を import してくれないことがあるので注意
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.R

// いちいちプロジェクトを作り直すのが面倒なので全てここで済ませる
@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        // 利用可能なスペースをコンポーネントで埋めること
        // つまり、与えられている端末領域の縦横幅を全て使うっていうことだよね
        .fillMaxSize()
        // 利用可能なスペースが少なくとも内部のコンポーネントと同じ大きさにするよ
        // 絵で考えるとわかりやすい
        // 面積不変の四角形の中に面積可変の四角形を入れ、面積可変の四角形が横に膨張しても縦に膨張しても、外側の不変の四角形の面積を最大とするよってことだね
        // 反対に、可変の四角形が小さすぎる場合は不変の四角形のどこの位置に可変四角形を配置するのか？を定義しないといけない
        // そのため、Alignment.center を指定しているってこと
        .wrapContentSize(Alignment.Center)
    )
}

// Modifier
// Compose UI 要素を装飾したり、動作を追加したりする修飾要素の、順序付けされた不変のコレクション。
// たとえば、背景やパディング、クリックイベントリスナーなどが、行やテキスト、ボタンなどを装飾したり、動作を追加したりします。
// DiceWithButtonAndImage の modifier 変数の意味は、外部から modifier が渡されればそれに従うが、そうでない場合はデフォルトの Modifier を使うという意味。

// 疑問: デフォルトがあるのに、どうしてわざわざ Modifier 引数を渡す必要があるのか
// 回答: コンポーザブルが再コンポーズされる可能性があるため
@Composable
fun DiceWithButtonAndImage(
    modifier: Modifier = Modifier
) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "1")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
}