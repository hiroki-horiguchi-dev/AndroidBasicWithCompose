/// 学習内容: List, MutableList, Set, MutableSet, Map, MutableMap
val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")

fun main() {
    // List は不変
    val list = listOf(1,2,3,4,5,6,7,8,9)
    // MutableList は可変
    val mutableList = mutableListOf(1,2,3,4,5,6)
    mutableList.add(3)

    // 拡張 for っぽい書き方もできるよん
    for (element in mutableList) {
        println(element)
    }

    // mutableList は追加、削除、更新、特定の要素が含まれているか？(in 演算子)を使える
    if (2 in mutableList) {
        println("true")
        /// 出力: true
    }

    // Set
    // 重複NG

    // Map
    // key, value のセット
    // 特に学んだことはないかな

}