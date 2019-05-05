package com.example.my_qiita_app.ui

import androidx.lifecycle.*
import com.example.my_qiita_app.data.ArticleEntity
import com.example.my_qiita_app.domain.ArticleUseCase
import kotlinx.coroutines.launch

class ArticleViewModel(private val useCase: ArticleUseCase) : ViewModel(), LifecycleOwner {
    val articles = MutableLiveData<List<ArticleEntity>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() = viewModelScope.launch {
        load()
    }
    
    private suspend fun load() {
        try {
            articles.value = useCase.getArticles()
        } catch(e: Throwable) {
            // TODO: エラーハンドリング
        }
    }
}