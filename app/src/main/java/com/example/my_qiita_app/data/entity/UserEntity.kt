package com.example.my_qiita_app.data.entity

import com.squareup.moshi.Json

data class UserEntity (
    @field:Json(name = "profile_image_url")
    val profileImageUrl: String = ""
)