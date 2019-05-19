package com.example.my_qiita_app.data

import com.squareup.moshi.Json

data class TagEntity (
    @Json(name = "name")
    val name: String
)