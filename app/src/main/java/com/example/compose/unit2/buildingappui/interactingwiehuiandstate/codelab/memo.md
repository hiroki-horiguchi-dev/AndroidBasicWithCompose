## 学び
- デリゲートについて
- remember, ReComposition について
- テスト可能にするために internal 修飾子へ変更する
- @VisibleForTesting このアノテーションを付与することでテスト用に公開されていることがわかる
- 結合テストは使うならもう少し勉強した方が良い
  - https://developer.android.com/codelabs/basic-android-kotlin-compose-write-automated-tests?hl=ja&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-2-pathway-3%3Fhl%3Dja%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-write-automated-tests#4

### デリゲート(by) について
- Java で書いた場合を考えると楽だよ
```kotlin
package delegation

interface Nameble {
    var name: String
}

class JackName : Nameble {
    override var name: String = "Jack"
}

class Person(name: Nameble) : Nameble {
    override var name: String = name.name
}

fun main() {
    val person = Person(JackName())
    println(person.name)
}
```

Kotlin delegate 利用例
```kotlin

interface Nameble {
    var name: String
}

class JackName : Nameble {
    override var name: String = "Jack"
}

class Person(name: Nameble) : Nameble by name

fun main() {
    val person = Person(JackName())
    println(person.name)
}
```