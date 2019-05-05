package com.example.my_qiita_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_qiita_app.data.ArticleEntity
import com.example.my_qiita_app.domain.ArticleUseCase

class ArticleViewModel(private val useCase: ArticleUseCase) : ViewModel(){
    val articles = MutableLiveData<List<ArticleEntity>>()

    suspend fun load() {
        try {
            articles.value = useCase.getArticles()
        } catch(e: Throwable) {
            // TODO: エラーハンドリング
        }
    }
}