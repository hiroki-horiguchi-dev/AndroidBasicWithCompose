## Activity ライフサイクルについて
読むだけで十分な内容だった。

```kotlin
onCreate
onStart
onResume
onPause
onStop
onDestroy
```

らへんのお話。
画面回転すると再コンポーズされるから画面上の値が保持できないよって話。
`remember` ではなく、`rememberSaveable` を使おうねって話。
SavedInstanceState じゃないんだねえ、へ〜って感じ。以上。