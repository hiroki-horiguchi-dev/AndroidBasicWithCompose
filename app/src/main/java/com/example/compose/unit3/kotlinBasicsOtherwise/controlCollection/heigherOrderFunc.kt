package com.example.compose.unit3.kotlinBasicsOtherwise.controlCollection

// memo
// そんな難しくないよ
// コレクションを操作するための高階関数を学ぶ
// map, filter, groupBy, fold, sortedBy

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

fun main() {
    // forEach(action: (T) -> Unit)
    // ジェネリクスなので方は何でも良く、it でコレクションの要素を一意に識別する識別子にアクセスできる
    cookies.forEach {
        // println("Menu item: $it.name")
    }

    // map
    // あるコレクションを同じ数の要素を持つコレクションに変換できる
    // 例. Cookie リストの中にある name のみを収集する別のリストを作成する
    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    // println(fullMenu)

    // filter
    // コレクションのサブセットを作成できる
    // 例. 数値のリストに対して filter を使用し、偶数のみを含む新しいリストを作成できる
    // イテレーターのはなしかな、元のリストをコピーして別の長さのリストを再生成するイメージ
    // 配列に含まれる要素が持つプロパティに対して、filter をかけて別のものを作りたい時に便利
    // val softBakedMenu = cookies.filter {
        // it.softBaked
    // }
    // println("Soft cookies:")
    // softBakedMenu.forEach {
        // println("${it.name} - $${it.price}")
    // }

    // groupBy
    // 関数に基づいてリストをマップに変換できる
    // 関数の一位の戻り値はそれぞれが結果のマップのキーとなる
    val groupedMenu = cookies.groupBy {
        // 戻り値は　Map<Boolean, List<Cookie>>
        it.softBaked
    }
    // Map<Boolean, ...> で key が Bool なので、それぞれのリストに分ける
    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    // println("Soft cookies:")
    softBakedMenu.forEach {
        // println("${it.name} - $${it.price}")
    }

    // println("Crunchy cookies:")
    crunchyMenu.forEach {
        // println("${it.name} - $${it.price}")
    }

    // fold
    // コレクションから単一の値を生成するために使用する、
    // 合計価格を計算する場合、リスト内すべての要素を合計して平均値を求める場合などに使う。
    // 第一引数に初期値 (今回はドルなので Double 型の 0.0)
    // 第二引数に演算を行うラムダ式
    // 処理的には、一回一回計算を行う感じだな
    // 0.0 に最初の cokkies.price が渡されて、、、みたいな感じ
    val totalPrice = cookies.fold(0.0) {total, cookie ->
        total + cookie.price
    }
    // println("Total price: $${totalPrice}")
    // sum 関数を使うとしたら、filter かけて作ったリストに対して実行でいけるが、fold の方が簡潔

    // sortedBy()
    // リストに対して使用できた sort() 関数はオブジェクトに対しては機能しない
    // このような場合に対処するため、sortedBy がある
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }
    println("Alphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }
}