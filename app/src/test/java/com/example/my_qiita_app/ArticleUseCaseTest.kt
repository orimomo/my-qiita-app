package com.example.my_qiita_app

import com.example.my_qiita_app.data.ArticleRepository
import com.example.my_qiita_app.data.entity.ArticleEntity
import com.example.my_qiita_app.domain.ArticleUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleUseCaseTest {
    // Repositoryのモックインスタンスの生成
    private val mockRepository = mockk<ArticleRepository> {
        // パターンの設定
        coEvery { getArticles(any(), any()) } returns listOf(ArticleEntity(), ArticleEntity())
    }

    // モックインスタンスをuseCaseに注入
    private val useCase = ArticleUseCase(mockRepository)

    @Test
    fun getArticles() = runBlocking {
        // メソッドを呼び出してlistに格納
        val list = useCase.getArticles("555", "kotlin")

        // メソッドが正しく呼び出されたことのチェック
        coVerify { mockRepository.getArticles("555", "kotlin") }
        // 正しい結果が得られたことのチェック
        assertEquals(2, list.size)
    }
}
