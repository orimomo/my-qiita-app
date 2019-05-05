package com.example.my_qiita_app.domain

import com.example.my_qiita_app.data.ArticleEntity
import com.example.my_qiita_app.data.ArticleRepository

class ArticleUseCase(private val articleRepository: ArticleRepository) {
    suspend fun getArticles(page: String = "1", parPage: String = "10"): List<ArticleEntity> {
        return articleRepository.getArticles(page, parPage)
    }
}