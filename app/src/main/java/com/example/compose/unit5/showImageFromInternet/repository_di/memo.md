### UI レイヤとデータレイヤを分離する

推奨アーキテクチャ

![img.png](img.png)

- UI Layer
- Data Layer
の存在が必須(domain Layer は必須ではない)


Data Layer の構成要素

![img_1.png](img_1.png)

- Repository が Data Sources の操作を全て提供し、カプセル化しておく
- UI Layer から Data Sources を直接操作しない

![img_2.png](img_2.png)


#### データレイヤの追加

![img_3.png](img_3.png)

ViewModel で data に直接アクセスするのではなく、Repository を介しただけ。

![img_4.png](img_4.png)

