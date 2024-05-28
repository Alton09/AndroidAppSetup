package com.johnqualls.android.appsetup.articles.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnqualls.android.appsetup.articles.data.ArticleRepository
import com.johnqualls.android.appsetup.core.ui.Presenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val articleRepository: ArticleRepository) : Presenter<ArticleUiEvent, ArticleUiState>, ViewModel() {
    override val uiState: StateFlow<ArticleUiState>
        get() = _uiState.asStateFlow()

    private val _uiState = MutableStateFlow(ArticleUiState())

    init {
        viewModelScope.launch {
            try {
                val articles = articleRepository.getArticles()
                updateState { it.copy(articles = articles) }
            } catch (e: Exception) {
                Log.e("JAQ", e.message, e)
                // TODO Add error state
            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }

    override fun receiveEvent(event: ArticleUiEvent) {
        TODO("Not yet implemented")
    }

    override fun updateState(function: (ArticleUiState) -> ArticleUiState) {
        _uiState.update(function)
    }
}

class ArticlePresenterFake : Presenter<ArticleUiEvent, ArticleUiState> {

    override val uiState: StateFlow<ArticleUiState>
        get() = MutableStateFlow(ArticleUiState())

    override fun updateState(function: (ArticleUiState) -> ArticleUiState) {
        // no-op
    }

    override fun receiveEvent(event: ArticleUiEvent) {
        // no-op
    }
}
