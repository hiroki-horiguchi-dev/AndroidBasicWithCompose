## forEach
コレクションないの要素をループできる。
拡張for と大してかわらん
```kotlin
for(listElement in list) {
    println(listElement)
}
```
これと同じ感じでこうかける
```kotlin
list.forEach {
    println(it.name)
}
```

## map
コレクション内のアイテムを書式設定できる。
というよりは、いい感じにコレクションからデータを抽出して色々作れるよって話。

## filter
コレクション要素の特定のプロパティに注目し、そのサブセットを生成できる

## groupBy
関数の戻り値に基づいてコレクションを分割する
こんな感じ
```kotlin
    val groupedMenu = cookies.groupBy {
        // 戻り値は　Map<Boolean, List<Cookie>>
        it.softBaked
    }
    // Map<Boolean, ...> で key が Bool なので、それぞれのリストに分ける
    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()
```

## fold
コレクションを単一の値に変換
合計値の計算に使える
```kotlin
    val totalPrice = cookies.fold(0.0) {total, cookie ->
        total + cookie.price
    }
```

## sortedBy
指定したプロパティでコレクションを並び替えられる
sort() はクラスオブジェクトには効かないので存在する感じ
```kotlin
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }
```