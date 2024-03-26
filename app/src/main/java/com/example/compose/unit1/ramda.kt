fun main() {
    val coins: (Int) -> String = {innerpadding ->
        "$innerpadding quarters"
    }
    val treatFunction = trickOrTreat(false, { "$it quarters" })
    val trickFunction = trickOrTreat(true, null)
    /// メモ
    /// 上記は変数に関数を代入した、ただの設定に過ぎない。
    /// 実際に以下で呼び出して初めて実行される
    treatFunction()
    trickFunction()
    repeat(4) {
        treatFunction()
    }
    val list = listOf<String>("aa","bb","cc")
    repeat(list.size) {
        println(list[it])
    }
}

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    return if (isTrick) {
        trick
    } else {
        if (extraTreat != null) {
            println(extraTreat(5))
        }
        treat
    }
}

val trick = {
    println("No treats")
}
val treat = {
    println("Have a treat")
}


