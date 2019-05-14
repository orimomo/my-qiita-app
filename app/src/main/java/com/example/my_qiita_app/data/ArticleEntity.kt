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
    @Json(name = "user")
    val user: UserEntity,
    @Json(name = "tags")
    val tags: List<TagEntity>,
    @field:Json(name = "created_at")
    val createdAt: String
) {
    fun getTagsString() : String {
        val builder = StringBuilder()
        builder.append("${tags.first().name}")
        if (tags.size >= 2) {
            for (i in 1..(tags.size-1))
            builder.append(", ${tags[i].name}")
        }
        return builder.toString()
    }
}