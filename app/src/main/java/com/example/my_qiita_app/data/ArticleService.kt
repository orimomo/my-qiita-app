package com.example.my_qiita_app.data

import com.example.my_qiita_app.data.entity.ArticleEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("api/v2/items")
    fun getArticlesAsync(
        @Query("page") page: String,
        @Query("query") query: String,
        @Query("par_page") perPage: String = "40"
    ): Deferred<List<ArticleEntity>>
}
