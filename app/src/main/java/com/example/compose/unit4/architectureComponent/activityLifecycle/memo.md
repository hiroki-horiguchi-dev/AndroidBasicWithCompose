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
`remember` ではなく、`rememberSaveable` を使うと保持できるよって話。
でも `viewModel` 使うなら `rememberSaveable` は不要だよな、、あと　`SavedInstanceState` じゃないんだねえ、へ〜って感じ。
Log は Log.d とか使え、以上。