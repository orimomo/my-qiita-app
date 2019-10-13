package com.example.my_qiita_app.data.entity

import com.squareup.moshi.Json
import java.io.Serializable

data class ArticleEntity(
    @Json(name = "id")
    val id: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "body")
    val body: String = "",
    @Json(name = "url")
    val url: String = "",
    @Json(name = "user")
    val user: UserEntity = UserEntity(),
    @Json(name = "tags")
    val tags: List<TagEntity> = listOf(),
    @field:Json(name = "likes_count")
    val likes: String = "",
    @field:Json(name = "created_at")
    val createdAt: String = ""
) : Serializable {
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