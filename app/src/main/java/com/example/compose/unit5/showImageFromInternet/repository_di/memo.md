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

#### 依存関係インジェクション
クラスで必要なオブジェクトを取得する方法は以下の 2つ.
1. クラスが必要なオブジェクトをインタンス化すること
   1. ![img_5.png](img_5.png)
   2. こちらの方法は簡単だが、クラスと必要なオブジェクトが見つけ都合されているので、コードの柔軟性がなく、テストが難しい
2. 必要なオブジェクトを引数として渡すこと
   1. ![img_6.png](img_6.png)
   2. コードの柔軟性と適応性を高めるためには、クラスが依存するオブジェクトをクラスの外でインスタンス化してから渡す必要がある。
   3. この方法では、クラスが特定の1つのオブジェクトにハードコードされなくなるので、より柔軟。
3. ![img_8.png](img_8.png) 


クラスの外でインスタンス化してオブジェクトを渡す場合、
![img_10.png](img_10.png)

ElectricEngine が追加で必要な場合、ElectricEngine のインスタンスを car に渡すことができる。

![img_9.png](img_9.png)

言いたいことはなんとなくわかるけど、言葉だと的をえないのでコードみる。
