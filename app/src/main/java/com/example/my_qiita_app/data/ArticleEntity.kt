package com.example.my_qiita_app.data

import com.squareup.moshi.Json

data class ArticleEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "url")
    val url: String,
    @field:Json(name = "created_at")
    val createdAt: String
)