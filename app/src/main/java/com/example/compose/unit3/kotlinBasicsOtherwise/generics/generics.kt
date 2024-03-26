fun main() {
    /// ジェネリクス, enum, data class
//    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
//    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
//    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)
//    println(question1.toString())
    // val question4 = question1.copy()
    // println(question4.toString())

    // singleton
    // println("${Quiz.answered} of ${Quiz.total} answered.")

    // 拡張プロパティへアクセス
    // println(Quiz.progressText)
    // 拡張関数
    // Quiz.printProgressBar()

    // interface で実装した printProgressBar 関数の呼び出し
    // Quiz().printProgressBar()

    // スコープ関数1 let
    // val quiz = Quiz()
    // quiz.printQuiz()

    // スコープ関数2 apply
    // インスタンスを参照せずにメソッド(複数でも)呼び出すことができる
    Quiz().apply {
        printQuiz()
        printProgressBar()
    }
    // スコープ関数を必ずしも使う必要はないが、簡潔に記載するのに役立つ
}

// シングルトン
//object StudentProgress {
//    var total: Int = 10
//    var answered: Int = 3
//}

/// when 使った時に else を書かなくていいのと、コンパイル時に追加漏れとかを指摘してくれるからいいよねえ
enum class Difficulty {
    EASY, MEDIUM, HARD
}

// インターフェース
interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}

class Quiz : ProgressPrintable {
    override val progressText: String
        get() = "$answered of $total answered"

    override fun printProgressBar() {
        repeat(answered) { print("▓") }
        repeat(total - answered) { print("▒") }
        println()
        println(progressText)
    }

    private val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    private val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    private val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    // シングルトン
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

    // スコープ関数便利よね
    // let でアクセスすると it に置き換えられるから便利
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
}

// TODO 拡張プロパティ、拡張関数は定義済みの 1 つのクラスに機能を追加するための優れた方法ですが、ソースコードにアクセスできる場合は、必ずしもクラスを拡張する必要はありません。代替として interface があるよに繋がった。
/// 拡張プロパティ
//val Quiz.StudentProgress.progressText: String
//    get() = "$answered of $total answered"

/// 拡張関数
//fun Quiz.StudentProgress.printProgressBar() {
//    repeat(Quiz.answered) { print("▓") }
//    repeat(Quiz.total - Quiz.answered) { print("▒") }
//    println()
//    println(Quiz.progressText)
//}

/// data class にすると、equals, hashCode, toString, componentN, copy メソッドを使うことができる
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

class FillInTheBlankQuestion(
    val questionText: String,
    val answer: String,
    val difficulty: String
)

class TrueOrFalseQuestion(
    val questionText: String,
    val answer: Boolean,
    val difficulty: String
)

class NumericQuestion(
    val questionText: String,
    val answer: Int,
    val difficulty: String
)