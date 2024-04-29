## 予習

⚠️多分 codelab ソースコードが古くて Jelly Fish で使っているコンパイルバージョンと合わないので、テキスト勉強のみとする。

#### DB とは？
![img.png](img.png)

これだと思っていいよ
id が主キーね

あるテーブルが別のテーブルのキーを参照するとき、そのキーのことを外部キーと呼ぶよ

知ってるわ！

#### SQLite ってなあに

Kotlin と SQL のデータ型一致表
![img_1.png](img_1.png)

スキーマが総称だよ
![img_2.png](img_2.png)

#### DB Inspector

![img_3.png](img_3.png)

Database Inspector で端末の DB テーブルを見ることができるよ。

![img_4.png](img_4.png)

実際にクエリも叩けるよ

#### SELECT

言葉の定義をちゃんと覚えておけば問題ないね

```text
Table: データの概要分類
Column: data that each row in the table contains.
Row:  actual data that consist of values for each column in the table.
```

イメージ
Columnは、列、縦
![img_7.png](img_7.png)

Row は、行、横
![img_8.png](img_8.png)

Compose と同じ

![img_9.png](img_9.png)

基本構文

![img_10.png](img_10.png)

こういう DB があった時、
```roomsql
SELECT * FROM email;
```

これでemail table のすべての Row を取得

```roomsql
SELECT subject FROM email;
```
これで subject Column を取得
![img_11.png](img_11.png)

```roomsql
SELECT subject, sender FROM email;
```

これで、subject, sender column を取得

![img_12.png](img_12.png)

#### Reduce columns with aggregate functions
```roomsql
SELECT subject FROM email;
SELECT subject, sender FROM email;
```
この辺で取得した結果に対して、フィルターやその他関数を使いたい時のやつ

![img_18.png](img_18.png)
max, min, sum ,count, average あたり使えるね

公式はこれ
![img_17.png](img_17.png)

例
```roomsql
SELECT COUNT(*) FROM email;
SELECT MAX(received) FROM email;
```

結果
![img_19.png](img_19.png)
![img_20.png](img_20.png)

#### Filter duplicate results with DISTINCT
重複排除

![img_21.png](img_21.png)

例
```roomsql
SELECT DISTINCT sender FROM email;
```

![img_22.png](img_22.png)

aggregate functions と併用することもできるよ
![img_23.png](img_23.png)

例
```roomsql
SELECT COUNT(DISTINCT sender) FROM email;
```

![img_24.png](img_24.png)

#### Filter queries with a WHERE clause

where 使ってクエリのフィルタかけようね

![img_25.png](img_25.png)

例
inbox が意味不明なので説明すると、ユーザーはメールボックスに trash とか spam とか作ってるかもねと
取得する email は inbox フォルダのものに限りたい、みたいな意図で書いてるみたいだね
普通テーブル分けるんじゃないの。。。？

```roomsql
SELECT * FROM email
WHERE folder = 'inbox';
```

⚠️ 注意点
kotlin と違って等値の評価は = で行うよ
![img_26.png](img_26.png)

#### Logical operators with WHERE clauses
if( true && false) みたいな、&& できるよ

AND 演算
![img_27.png](img_27.png)

OR 演算
![img_28.png](img_28.png)

NOT 演算
![img_29.png](img_29.png)

例
```roomsql
SELECT * FROM email
WHERE folder = 'inbox' AND read = false;
```

```roomsql
SELECT * FROM email
WHERE folder = 'important' OR starred = true;
```

#### Search for text using LIKE
Rowに対して text 検索ができるよ

![img_30.png](img_30.png)

全文一致
![img_31.png](img_31.png)

kotlin String.startsWith みたいなやつ
![img_32.png](img_32.png)

kotlin String.endWith みたいなやつ
![img_33.png](img_33.png)

例(組み合わせ)
```roomsql
SELECT COUNT(*) FROM email
WHERE subject LIKE '%fool%';

SELECT * FROM email
WHERE subject LIKE '%fool';

SELECT DISTINCT sender FROM email
WHERE sender LIKE 'h%';
```

結果
![img_34.png](img_34.png)

![img_35.png](img_35.png)

![img_36.png](img_36.png)


#### Group results with GROUP BY
group By: がさっと持ってきたデータに対して、それぞれの持つ任意のプロパティでまとめたい。というイメージかな

![img_37.png](img_37.png)

例
```roomsql
SELECT folder, COUNT(*) FROM email
GROUP BY folder;
```

![img_38.png](img_38.png)

#### ORDER BY
Order By: ソートしたい時、デフォルトは昇順、昇順以外にしたい場合に使う。

例
```roomsql
SELECT * FROM email
ORDER BY received DESC;
```
![img_39.png](img_39.png)

`reveived` で降順。
![img_40.png](img_40.png)

⚠️ 注意点
GROUP BY と ORDER BY を併用したい場合、GROUP BY が先ね
![img_41.png](img_41.png)


#### Restrict the number of results with LIMIT

LIMIT: 取得数の制限
![img_42.png](img_42.png)

OFFSET: 最初の20行のうち、11~20 行が欲しい場合に以下のように設定してね
![img_43.png](img_43.png)
それぞれに 10 が入るイメージ。

例
```roomsql
SELECT * FROM email
WHERE folder = 'inbox'
ORDER BY received DESC
LIMIT 10;
```

```roomsql
SELECT * FROM email
WHERE folder = 'inbox'
ORDER BY received DESC
LIMIT 10 OFFSET 10;
```

#### CRUD (Insert, update, and delete data in a database)

INSERT: Create row in table

![img_44.png](img_44.png)

例
```roomsql
INSERT INTO email
VALUES (
    NULL, 'Lorem ipsum dolor sit amet', 'sender@example.com', 'inbox', false, false, CURRENT_TIMESTAMP
);
```
最後尾に挿入されるかな、id が主キー

⚠️ CURRENT_TIMESTAMP について
特別変数って言ってるけど、デフォで UTC のタイムスタンプが入るだけ
![img_45.png](img_45.png)

UPDATE: update table in table

![img_46.png](img_46.png)
Set 詳細
![img_47.png](img_47.png)
要するにクラスのインスタンス作るのと変わらないよね

![img_48.png](img_48.png)
基本 WHERE が必要ですと、当然だろ。

例
```roomsql
UPDATE email
SET read = true
WHERE id = 44;
```
email table, id = 44 read flag を true に変えたい

```roomsql
SELECT read FROM email
WHERE id = 44;
```
結果が正しいか取得、ちなみに 0: false, 1: true


DELETE: そのまんま

![img_49.png](img_49.png)

table から 条件(whereで指定)のRow(行)を削除 
![img_50.png](img_50.png)


#### Summary

![img_51.png](img_51.png)

SELECT: 選択したテーブルから何を取る？
FROM: どのテーブルを取る？
WHERE: 取得したい Row の条件は？
GROUP BY: 取得した Row のデータが持つカラムプロパティのうち、何でまとめたい？
ORDER BY: 取得したデータの並び順をどうしたい？
LIMIT: 取得するデータの件数どうしたい？
OFFSET: 取得するデータが複数件ある場合、最初の何件スキップしたい？

以上！！！