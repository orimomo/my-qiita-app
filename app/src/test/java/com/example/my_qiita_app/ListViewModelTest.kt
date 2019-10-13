package com.example.my_qiita_app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.my_qiita_app.data.entity.ArticleEntity
import com.example.my_qiita_app.domain.ArticleUseCase
import com.example.my_qiita_app.ui.list.ListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {
    // LiveDataをテストするために必要
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockUseCase = mockk<ArticleUseCase> {
        coEvery { getArticles(any(), any()) } returns listOf(ArticleEntity(), ArticleEntity())
    }

    private val viewModel = ListViewModel(mockUseCase)

    @Test
    fun load() = runBlocking {
        viewModel.load()

        coVerify { mockUseCase.getArticles(any(), any()) }
        assertEquals(2, viewModel.kotlinArticles.value?.size)
        assertEquals(ListViewModel.Status.COMPLETED, viewModel.status.value)
    }
}