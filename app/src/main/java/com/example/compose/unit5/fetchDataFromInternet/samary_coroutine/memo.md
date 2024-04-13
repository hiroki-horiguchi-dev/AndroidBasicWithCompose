### 同期コード

![img_1.png](img_1.png)

```kotlin
fun main() {
    println("Weather forecast")
    println("Sunny")
}
```

![img_2.png](img_2.png)

#### 遅延を追加する

```kotlin
import kotlinx.coroutines.*

fun main() {
    println("Weather forecast")
    delay(1000)
    println("Sunny")
}
```

![img_3.png](img_3.png)

![img_4.png](img_4.png)

```kotlin
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        delay(1000)
        println("Sunny")
    }
}
```

![img_5.png](img_5.png)


![img_6.png](img_6.png)

#### 関数の中断

![img_7.png](img_7.png)

```kotlin
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        printForecast()
    }
}

fun printForecast() {
    delay(1000)
    println("Sunny")
}
```

delay は suspend 関数なのでそれを含んでいる printForecast() も suspend 関数だよねって話し。

suspend 関数は通常の関数に似ていますが、中断して後で再開できる。
そのために、suspend 関数は、この機能を利用できるようにする他の suspend 関数からしか呼び出すことができない。

suspend 関数には 0 個以上の中断ポイントを含めることができる。
中断ポイントは、関数の実行を中断できる、関数内の場所。
実行が再開されると、コード内の最後に中断したところから再開して、関数の残りの部分に進む。
具体的なコードが欲しいね。

```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Weather forecast")
        printForecast()
        printTemperature()
    }
}

suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}
```

```text
// 出力   
    Weather forecast
    Sunny
    30°C
Process finished with exit code 0
```

![img_8.png](img_8.png)

![img_9.png](img_9.png)


### 非同期コード

![img_10.png](img_10.png)
に従っています。

```kotlin
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        launch {
            printForecast()
        }
        launch {
            printTemperature()
        }
    }
}

suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}
```

![img_11.png](img_11.png)

```kotlin
fun main() {
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}
```

```text
Weather forecast
Sunny
30°C
Execution time: 1.065 seconds
```

![img_12.png](img_12.png)

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        launch {
            printForecast()
        }
        launch {
            printTemperature()
        }
        println("Have a good day!")
    }
}
```

```text
Weather forecast
Have a good day! <-- ここに注目ね
Sunny
30°C
Execution time: 1.063 seconds
```

これが launch を使った非同期処理
Have a good day は先に実行される

#### async()

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        val forecast: Deferred<String> = async {
            getForecast()
        }
        val temperature: Deferred<String> = async {
            getTemperature()
        }
        println("${forecast.await()} ${temperature.await()}")
        println("Have a good day!")
    }
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}
```

![img_13.png](img_13.png)

#### 並列分解

![img_14.png](img_14.png)

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}
```

![img_15.png](img_15.png)

![img_16.png](img_16.png)


### 例外とキャンセル

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}
```

重要なのは、getTemperture で落ちるのが runBlocking に伝播して、
getForecast も同時に落ちること。

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        try {
            println(getWeatherReport())
        } catch (e: AssertionError) {
            println("Caught exception in runBlocking(): $e")
            println("Report unavailable at this time")
        }
        println("Have a good day!")
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}
```

```text
Weather forecast
Caught exception in runBlocking(): java.lang.AssertionError: Temperature is invalid
Report unavailable at this time
Have a good day!
```

気温は取得できた方がよくね？ということで。

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async {
        try {
            getTemperature()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }

    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}
```

```text
Weather forecast
Caught exception java.lang.AssertionError: Temperature is invalid
Sunny { No temperature found }
Have a good day!
```

![img_17.png](img_17.png)


#### キャンセル

```kotlin
fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }

    delay(200)
    temperature.cancel()

    "${forecast.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}
```

```text
Weather forecast
Sunny
Have a good day!
```

![img_18.png](img_18.png)

![img_19.png](img_19.png)


### コルーチンのコンセプト

![img_20.png](img_20.png)

#### ジョブ

launch{} の戻り値は Deferred (Deferred : Jobって感じかな)であり、当然 Job でもあるってこと。
当然なのは継承しているっぽいので。
ジョブはタスクが不要になったらこルーチンをキャンセルするなど、存在期間を制御するために使用できるよと。
Java の　Thread.stop() みたいなことを言ってるな多分。


ジョブの階層について

![img_22.png](img_22.png)

![img_23.png](img_23.png)

親のキャンセルはこのキャンセル
子のキャンセルは子のみに適用される
子ジョブが例外で失敗した場合、親ジョブもキャンセルされる。
    回避するには try-catch で拾うしかない

![img_24.png](img_24.png)


![img_25.png](img_25.png)


![img_26.png](img_26.png)

おっしゃる通りですね

![img_27.png](img_27.png)


#### CoroutineContext

![img_28.png](img_28.png)

![img_29.png](img_29.png)

![img_30.png](img_30.png)

やべえ意味わかんねえ

#### ディスパッチャ

何言ってんのか意味わからないんだけど、Android には UI スレッドとバックグラウンドスレッドがある。
UI スレッドは UI の更新、操作の迅速な処理のために使われる。
API 通信などは当然バックグラウンドスレッドを使うべきなので、Dispatchers.IO を選択するべきだよねって話。

![img_31.png](img_31.png)

![img_33.png](img_33.png)

```kotlin
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        launch {
            delay(1000)
            println("10 results found.")
        }
        println("Loading...")
    }
}
```

![img_34.png](img_34.png)

```kotlin

fun main() {
    runBlocking {
        launch {
            withContext(Dispatchers.Default) {
                delay(1000)
                println("10 results found.")
            }
        }
        println("Loading...")
    }
}
```

WithContext() は suspend関数なので、ディスパッチャの切り替えが可能。
指定されたコードブロックを新しい CoroutineContext を使用して実行できる。
つまり、親の launch {} で適用された Dispatchers.Main が、withContext(){} で指定した Dispatchers.Default にオーバーライドされましたよっと。

```text
// 出力
Loading...
10 results found.
```

![img_35.png](img_35.png)

```kotlin
fun main() {
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
}
```

何が言えるのか？
明示的にスレッドのディスパッチャを指定しない場合、基本的に UI スレッドで処理が実行される。
行う処理に応じてそれぞれに適切なスレッドを指定してあげないといけないよねって話。

![img_36.png](img_36.png)

Room, Retrofit は勝手に Dispathers.IO で処理を行ってくれるので何も考えなくてもいいけど、本当は自分で明示的に変える必要があるんだよ？って話。
便利にしてくれてありがたいね。

### まとめ

![img_37.png](img_37.png)

概要として、結局重要なのは async, launch の挙動の違いをまとめて終了とする。