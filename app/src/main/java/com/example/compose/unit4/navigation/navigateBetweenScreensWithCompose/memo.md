### ルート定義
![img.png](img.png)

イメージは URL と同様とのことなので、Webアプリの root と同じような発想で良さそう。


### NavHost

![img_1.png](img_1.png)

![img_2.png](img_2.png)

- 設定方法
enum で定義した画面の名称に対して Navhost でルートを指定してあげた感じ。
実際のルート間移動はこの後に実装
![img_3.png](img_3.png)

- 別のルートに移動

![img_4.png](img_4.png)

![img_5.png](img_5.png)

の

![img_6.png](img_6.png)

で、Stack に貯めていた画面を破棄して、指定した画面に戻ることができるよと。
Fragment の popbackstack と似ているよねと。

### 別のアプリに移動する

Intent とか使うやつかな
![img_7.png](img_7.png)

### 戻るボタン(OS標準とは別で Appbar に出すやつ)

![img_8.png](img_8.png)

呼び出す側で色々やっているのでこれも参考になりそうだよ
特に、画面に対応する画面名を AppBar に表示する際に使っている、バックスタックに積まれている情報を取得するところとかね。

![img_9.png](img_9.png)

スタックの状態を確認する処理を書きはするんだけど、結局戻るボタンを押した時の挙動はこれで管理するのが妥当のようだね。

![img_10.png](img_10.png)

![img_11.png](img_11.png)