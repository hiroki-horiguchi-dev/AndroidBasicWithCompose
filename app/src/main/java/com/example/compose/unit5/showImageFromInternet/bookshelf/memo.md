## 始める前に

### 前提条件
Android Studio でプロジェクトを作成、実行できること
Jetpack Compose でレイアウトを作成できること
Kotlin のコルーチンの使用経験
Retrofit、Coil、Gson の使用経験

### 作成するアプリの概要
ウェブサービスに対して複数のリクエストを実行し、ダウンロードした非同期画像を表示する Android アプリ。

### 必要なもの
Android Studio がインストールされているパソコン

## 2. 概要

これでユニット 5 は完了です。

コルーチン、Retrofit、Gson など、このユニットで学習したコンセプトを実践的に確認するために、
Google Books API から取得した画像とともに書籍の一覧を表示するアプリを独自に作成します。

このアプリには次の動作を実装します。

- Retrofit を使用して Google Books API にリクエストを送信する
- Gson を使用してレスポンスを解析する
- 非同期にダウンロードした書籍の画像を、書籍名とともに縦方向グリッドで表示する
- リポジトリを使用して UI とデータレイヤを分離するベスト プラクティスを実装する
- 依存関係の注入を使用して、ネットワーク サービスを必要とするコードのテストを作成する。

![img.png](img.png)

## 3. アプリを計画する

### UI を計画する
アプリの UI は自由に設計できます。
さまざまなデバイスのフォーム ファクタに、アプリのレイアウトがどのように適応するかを考慮する必要があります。

画像のスクロール グリッドを使用するため、複数の画像を画面上に同時に読み込む必要があります。
画像の URL を取得したら、Coil ライブラリが提供する AsyncImage コンポーザブルを使用して、バックグラウンドでデータをダウンロードできます。
アプリがネットワークを使用する際には、そのことを可能な限りユーザーに示すようにします。

### ネットワーク レイヤを計画する
このユニットのパスウェイ 1 では、ネットワークからデータを取得し、JSON レスポンスを解析する方法を学習しました。
Bookshelf アプリの場合、データレイヤで次の 3 つのことを行う必要があります。

1. Google Books API からデータを取得する Retrofit サービスを作成する。 
2. 書籍の一覧や特定の書籍の情報を取得するサービスのメソッドを追加する。 
3. Gson を使用して、API から返された JSON レスポンスから意味のあるデータを抽出する。

では、このプロジェクトで必要な Google Books API のメソッドを簡単に説明しましょう。

#### 書籍を検索する

Google Books API には、API を使用するで説明されているように、
特定の検索キーワードに基づいて書籍のリストを返すメソッドが用意されています。

たとえば、この URL は「ジャズの歴史」という用語の検索結果を返します。

例
https://www.googleapis.com/books/v1/volumes?q=jazz+history
検索を絞り込むためのクエリ パラメータはいくつかあります。
Bookshelf アプリでは、q パラメータ（クエリの略）で十分です。

ドキュメントには、想定される JSON レスポンスも記載されています。
Bookshelf アプリの場合、書籍の id を抽出する必要があります。

#### 特定の書籍の情報をリクエストする

リクエストを送信し、特定の書籍に関する情報を取得する必要があります。
このエンドポイントは、直前のレスポンスから抽出した ID を受け取ります。

https://www.googleapis.com/books/v1/volumes/<volume_id>
サムネイル リンクは volumeInfo オブジェクトの imageLinks オブジェクト内にあります。
このアプリの場合、ダウンロードする画像は thumbnail キーの下にあります。

```json
    "imageLinks": {
      "smallThumbnail": "http://books.google.com/books/publisher/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE734s3CngIs16gM_Ht6GeGF4ew664I7oOGghmfk4pgfFcDYb4GlYCYdjtqqXluL2KUyfq_Ni5MSyv4JxEJ8W679zQ2Ib3okUKau3I1ruqBGrWOt2_haUauWC8sXEgjN7JHm4uOjS&source=gbs_api",
      "thumbnail": "http://books.google.com/books/publisher/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE71N0ldzv6rliUV_K5ZACa9yPNcT8Ino6YKXJUMje_z4GsC9cp6gFql5TxlmqMoYN3CDhM3XAIO2riFeMXUnFVr5pTLq91htTtG1DDyvOdiR4yI6xu3yEEAn0dRbvNFZ5m7dUC9E&source=gbs_api",
      "small": "http://books.google.com/books/publisher/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=2&edge=curl&imgtk=AFLRE71HmTwpoe3KR0AISYk5sDgV2Fz-F-6CDKJtFdvlXSZv3jEzFtsSXGJnEGjtCuoDMxP_6sgP8au1yadB7OmI2MhIBquel7ivcDB8e9ieLyh4HNoXnX3zmxfF_CfIfnNXDv0WHuyA&source=gbs_api",
      "medium": "http://books.google.com/books/publisher/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=3&edge=curl&imgtk=AFLRE72LMPH7Q2S49aPeQ3Gm8jLEf6zH4ijuE0nvbOyXBUAgyL816pXzaw0136Pk8jXpfYYFY0IsqL7G7MMDMgKcJhnaoHojWNZpljZmGHeWLL_M7hxkOpmdmO7xza8dfVfPbFmBH4kl&source=gbs_api",
      "large": "http://books.google.com/books/publisher/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=4&edge=curl&imgtk=AFLRE71w0J9EOzUzu1O5GMbwhnpI8BLWzOEtzqc9IfyxEDqimZ--H4JlNAZh_1zx8pqPNRf1qDt7FPb57lH5ip-LBlK3zjMC-MCBYcciuoPjTJOFmLv7pp5B6_-UFBap1KRfC0eG7P4d&source=gbs_api",
      "extraLarge": "http://books.google.com/books/publisher/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=6&edge=curl&imgtk=AFLRE73t0gcxT-jzEETp8Yo5Osr15nVL7ntKL2WSe2S8kRSio7w0CGgErAq4WbPWIsH4TmOdP_EO6ZoPNSP-YGSOwqfPMw8_IlYE6hy9IKeAs5V_xaHy7drZleF0eizAQiEVg5ci7qby&source=gbs_api"
    },
```

なるほどねん、、、
このコードラボの本質は 2回連続で API を叩く練習をしてねっていうことなんだね。
しかも、一回目が完全に終了してから2回目を叩く、みたいな叩き方をしてねってことだ

一回、https://www.googleapis.com/books/v1/volumes?q=jazz+history を叩いて、
`"id": "0LscEAAAQBAJ"`をクエリに入れて、
https://www.googleapis.com/books/v1/volumes/<id> を叩いて、そのレスポンスの imageLinks をぶち込んだ mutableStateFlow を作って、
asStateFlow に変換して GridColumn みたいなやつで表示すればいいね

そのための Coroutine 処理は以下みたいにすればいいかな？
(ここは予習なので間違っていてもいいや)
```kotlin
class ViewModel(): ViewModel() {
    
    val _mutableBookDetailList: MutableStateFlow<List<DetialBook>> = MutableStateFlow(emptyList())
    val bookDetailList: StateFlow<List<DetailBook>> = _mutableBookDetailList.toList()
    
    fun fetch() {
        viewModelScope.launch{
            val books = fetchBooks()
            for (book in books) {
                _mutableBookDetailList.value.add(fetchBookDetail(book.id))
            }
        }
    }
    
    suspend fun fetchBooks(): List<Book> {
        repository.fetchBooks()
    }
    suspend fun fetchBookDetail(id: String): DetailBook {
        repository.fetchBookDetail(id)
    }
}
```

面倒なのが 2つの API でエラー画面を出し分けたい時にどうするのか？って話なんだよな。。
こうすればいけるのでは？
コルーチンは一つだけだけどこれでいいのかな？

```kotlin

import android.net.http.HttpException
import retrofit2.HttpException
import java.io.IOException

sealed interface BookShelfApiState {
    data class Success(val bookDetailList: List<DetailBook>)
    object Loading

    // 2つの API を同時に叩くので、それぞれのエラーに応じたエラー画面を出す面倒臭い場合を想定
    // 詳細情報取得 API エラーの場合
    data class DetailBookError(val error: Error)
    // 本情報取得 API エラーの場合
    data class BooksError(val error: Error)
}


class ViewModel() : ViewModel() {

//    val _mutableBookDetailList: MutableStateFlow<List<DetialBook>> = MutableStateFlow(emptyList())
//    val bookDetailList: StateFlow<List<DetailBook>> = _mutableBookDetailList.toList()

    var bookShelfApiState: BookShelfApiState by mutableStateOf(BookShelfApiState.Loading)
        private set

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            val bookList: List<Book> = emptyList()
            bookShelfApiState = try {
                bookList = fetchBooks()
            } catch (e: IOException) {
                BookShelfApiState.BooksError(e)
            } catch (e: HttpException) {
                BookShelfApiState.BooksError(e)
            } catch (e: BookShelfAPiError) {
                /// 独自エラーをハンドリングしたい！みたいな場合にはこうするしかないか？
                BookShelfApiState.BooksError(e)
            }

            // bookList の要素数
            val bookListLength = bookList.size
            
            // bookList の id を使って取得した本の詳細情報を詰めるリスト
            val bookDetailList = emptyList<DetailBook>()
            for (book in bookList) {
                bookShelfApiState = try {
                    bookDetailList.add(
                        fetchBookDetail(book.id)
                    )
                    if (bookList.indexOf(book) == bookListLength) {
                        BookShelfApiState.Success
                    }
                } catch (e: IOException) {
                    BookShelfApiState.DetailBookError
                } catch (e: retrofit2.HttpException) {
                    BookShelfApiState.DetailBookError
                }
            }
        }
    }

    suspend fun fetchBooks(): List<Book> {
        repository.fetchBooks()
    }
    suspend fun fetchBookDetail(id: String): DetailBook {
        repository.fetchBookDetail(id)
    }
}
```

汚い！長い！ 
async, await 使えばもっと簡潔に綺麗に書けるか？？

ChatGpt はこんなコードを書いてくれた。
bookDetailList のところはこっちの方がいいね、for で回すより map で回して awaitAll の方が綺麗

```kotlin
private fun fetch() {
    viewModelScope.launch {
        bookShelfApiState = try {
            val bookList = fetchBooks()
            val bookDetailList = bookList.map { async { fetchBookDetail(it.id) } }.awaitAll()
            BookShelfApiState.Success(bookDetailList)
        } catch (e: IOException) {
            BookShelfApiState.BooksError(Error("Failed to fetch books."))
        } catch (e: HttpException) {
            BookShelfApiState.BooksError(Error("Failed to fetch books."))
        } catch (e: Exception) {
            BookShelfApiState.DetailBookError(Error("Failed to fetch book details."))
        }
    }
}
```

catch 以降のところがこっちの想定通りではないのでアレなので、どう書き換えるかね。。

Gemini に聞くとこうでた
こっちはコルーチンを親1つと子2つ起動して、bookList が空なら BookShelfApiState.BooksError にする。
bookList には Result が入るなら、Error を取ることもできそうな気がするんだが。。どうなんだ。。
```kotlin
private fun fetch() {
    viewModelScope.launch {
        val bookList = runCatching { fetchBooks() }.getOrElse { emptyList() }

        bookShelfApiState = when {
            bookList.isEmpty() -> {
                BookShelfApiState.BooksError
            }
            else -> {
                val bookDetailList = mutableListOf<DetailBook>()
                for (book in bookList) {
                    runCatching { bookDetailList.add(fetchBookDetail(book.id)) }
                }
                BookShelfApiState.Success(bookDetailList)
            }
        }
    }
}
```

予習はここまでで、あとは試しながらやってみる