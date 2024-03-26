package com.example.compose.unit2.buildingappui.addabuttontoapp.codelab.clickbehaivior

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.R

// 前回やった親要素で全画面かつ内部の Compose が外側以下ならセンターに配置する方法で実装
@Preview("clickBehavior", showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeScreen(
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                // TopAppBarの色を設定する
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = Color.Black,
                ),
                // TopAppBarのタイトルを設定する
                title = {
                    Text("Lemonade", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) {
        // ⚠️ラムダ式の復習
        // innerPadding -> 以下で書いてある型は以下の記述と同値
        // @Composable: (Int) -> Unit
        // コンポーズ型で Int を与えられたら、Int　を適用したコンポーズ型を返却するよってやつ
        // ちょっと複雑だけど、やっているのは ramda.kt に記載内容の coins を一部改造した
        // val coins: (Int) -> Unit = {innerPadding ->
        //    "innerPadding quarters"
        // }
        // と同じ。
        // Scaffold(){} の {} が Scaffold コンストラクタの content に渡され、
        // ScaffoldLayout 関数の中で innerPadding が決定され、
        // Column 以降が実行されるイメージ。
            innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // state を Enum で列挙型にしたのはいいと思う
            var state by remember {
                mutableStateOf(LemonadeState.LEMON_TREE)
            }

            // TODO ここはランダム値じゃなくて良い。デフォルトで適当な値を入れておかないと処理が重複する
            var randomClickCount by remember {
                mutableStateOf((2..4).random())
            }

            // TODO when を使った分岐が 3つもあるので統合すべき
            //  そうすると、必然的に回答コードのような実装になる
            val imageResource = when (state) {
                LemonadeState.LEMON_TREE -> R.drawable.lemon_tree
                LemonadeState.SQUEEZE_LEMON -> R.drawable.lemon_squeeze
                LemonadeState.LEMONADE -> R.drawable.lemon_drink
                LemonadeState.GLASS_EMPTY -> R.drawable.lemon_restart
            }

            val textResource = when (state) {
                LemonadeState.LEMON_TREE -> R.string.lemonTree
                LemonadeState.SQUEEZE_LEMON -> R.string.lemon
                LemonadeState.LEMONADE -> R.string.glass_of_lemonade
                LemonadeState.GLASS_EMPTY -> R.string.empty_glass
            }

            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 200.dp, height = 250.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Color.Green.copy(alpha = 0.5F)
                    )
                    .clickable {
                        when (state) {
                            LemonadeState.LEMON_TREE -> {
                                state = LemonadeState.SQUEEZE_LEMON
                                randomClickCount = (2..4).random()
                            }

                            LemonadeState.SQUEEZE_LEMON -> {
                                // squeeze 処理
                                if (randomClickCount == 0) {
                                    state = LemonadeState.LEMONADE
                                } else {
                                    randomClickCount--
                                }
                            }

                            LemonadeState.LEMONADE -> {
                                state = LemonadeState.GLASS_EMPTY
                            }

                            LemonadeState.GLASS_EMPTY -> {
                                state = LemonadeState.LEMON_TREE
                            }
                        }
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = textResource))
        }
    }
}

enum class LemonadeState {
    LEMON_TREE,
    SQUEEZE_LEMON,
    LEMONADE,
    GLASS_EMPTY
}
