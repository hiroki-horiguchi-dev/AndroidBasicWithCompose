### ジェネリクス
```kotlin
class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
```
answer の型を T にすることで以下のような呼び出しが可能になる

```kotlin
    private val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    private val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    private val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)
```
共通のプロパティを持つが, answer プロパティの型のみが異なる場合、
Question<> のなかで answer の型を与えてあげれば同じクラスで別の型を持つプロパティのインスタンスを作ることができる

### enum クラス
列挙型、まとめたい時に便利
```kotlin
/// when 使った時に else を書かなくていいのと、コンパイル時に追加漏れとかを指摘してくれるからいいよねえ
enum class Difficulty {
    EASY, MEDIUM, HARD
}
```

### データクラス
toString(), copy() などの便利なメソッドを使うことができる
```kotlin
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
```

### オブジェクトの式と宣言
シングルトン
```kotlin
    // シングルトン
    object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
```

あるクラス専用のシングルトン
companion 修飾子をつけてあげる感じ
```kotlin
    // シングルトン
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
```

拡張変数
⚠️必ずしも使うわけじゃないよ。swift の extensions と同じ感じなので。
```kotlin
    拡張プロパティ
    val Quiz.StudentProgress.progressText: String
        get() = "$answered of $total answered"
```


拡張関数
⚠️必ずしも使うわけじゃないよ。swift の extensions と同じ感じなので。
```kotlin
/// 拡張関数
    fun Quiz.StudentProgress.printProgressBar() {
        repeat(Quiz.answered) { print("▓") }
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(Quiz.progressText) 
    }
```

### スコープ関数
let
it で処理を置き換えられるのでプロパティへアクセスする必要がない、省略できて良い
```kotlin
    fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }
```

apply
クラスのインスタンスを格納したプロパティにアクセスしなくても処理を実行できる
```kotlin
    Quiz().apply {
        printQuiz()
        printProgressBar()
    }
```