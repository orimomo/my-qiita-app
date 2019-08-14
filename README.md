# my-qiita-app

## 概要
* 自分専用のQiitaクライアントアプリ
* Kotlin、Android、Swift、iOSのタグが付いている最新記事を表示する
* データ取得には[Qiita API v2](https://qiita.com/api/v2/docs)を使用
* 非同期処理ではCoroutine、画面遷移ではJetpack Navigationを使用

## デモ
![my_qiita_app_videl](https://user-images.githubusercontent.com/12453846/58762446-0574cd80-858b-11e9-8500-3dbccb6674cc.gif)

## 環境
* macOS: 10.14.1
* Android Studio: 3.4

## 使用しているライブラリ
### サポートライブラリ
* AndroidX

### API通信
* okhttp3
* retrofit2
* moshi

### DI
* Koin

### UI
* coroutines
* groupie
* glide
