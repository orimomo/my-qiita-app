package com.example.my_qiita_app.ui.list

import androidx.lifecycle.*
import com.example.my_qiita_app.data.entity.ArticleEntity
import com.example.my_qiita_app.domain.ArticleUseCase
import kotlinx.coroutines.launch

class ListViewModel(private val useCase: ArticleUseCase) : ViewModel(), LifecycleObserver {
    val kotlinArticles = MutableLiveData<List<ArticleEntity>>()
    val androidArticles = MutableLiveData<List<ArticleEntity>>()
    val swiftArticles = MutableLiveData<List<ArticleEntity>>()
    val iosArticles = MutableLiveData<List<ArticleEntity>>()
    val message = MutableLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @Suppress("UNUSED")
    fun onCreate() = viewModelScope.launch {
        load()
    }

    private suspend fun load() {
        try {
            kotlinArticles.value = useCase.getArticles("1", "tag:kotlin")
            androidArticles.value = useCase.getArticles("1", "tag:android")
            swiftArticles.value = useCase.getArticles("1", "tag:swift")
            iosArticles.value = useCase.getArticles("1", "tag:ios")
        } catch(e: Throwable) {
            message.value = "エラーが発生しました。"
        }
    }
}