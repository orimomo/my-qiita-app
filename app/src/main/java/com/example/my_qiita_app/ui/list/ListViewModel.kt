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
    val status = MutableLiveData<Status>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Suppress("UNUSED")
    fun onCreate() = viewModelScope.launch {
        load()
    }

    suspend fun load() {
        status.value = Status.LOADING
        try {
            kotlinArticles.value = useCase.getArticles("1", "tag:kotlin")
            androidArticles.value = useCase.getArticles("1", "tag:android")
            swiftArticles.value = useCase.getArticles("1", "tag:swift")
            iosArticles.value = useCase.getArticles("1", "tag:ios")
            status.value = Status.COMPLETED
        } catch(e: Throwable) {
            message.value = "エラーが発生しました。"
            status.value = Status.FAILED
        }
    }

    enum class Status {
        LOADING,
        COMPLETED,
        FAILED
    }
}