package com.johnqualls.android.appsetup.articles.ui

import androidx.lifecycle.ViewModel
import com.johnqualls.android.appsetup.core.ui.Presenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor() : Presenter<ArticleUiEvent, ArticleUiState>, ViewModel() {
    override val uiState: StateFlow<ArticleUiState>
        get() = _uiState.asStateFlow()

    private val _uiState = MutableStateFlow(ArticleUiState())

    override fun updateState(function: (ArticleUiState) -> ArticleUiState) {
        TODO("Not yet implemented")
    }

    override fun receiveEvent(event: ArticleUiEvent) {
        TODO("Not yet implemented")
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
