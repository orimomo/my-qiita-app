package com.example.my_qiita_app.data

import com.squareup.moshi.Json

data class ArticleEntity(
    @Json(name = "id") // 記事ID
    val id: String,
    @Json(name = "title") // タイトル
    val title: String,
    @Json(name = "body") // 記事の中身
    val body: String
)