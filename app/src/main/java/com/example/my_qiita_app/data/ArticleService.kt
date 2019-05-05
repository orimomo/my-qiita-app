package com.example.my_qiita_app.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("api/v2/items")
    fun getItems(
        @Query("page") page: String,
        @Query("par_page") perPage: String
//        @Query("query") query: String
    ): Deferred<List<ArticleEntity>>
}
