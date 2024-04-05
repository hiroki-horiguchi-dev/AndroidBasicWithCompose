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

