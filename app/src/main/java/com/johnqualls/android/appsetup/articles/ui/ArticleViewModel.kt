package com.johnqualls.android.appsetup.articles.ui

import androidx.lifecycle.ViewModel
import com.johnqualls.android.appsetup.core.ui.Presenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor() : Presenter<ArticleUiEvent, ArticleUiState>, ViewModel() {
    override val uiState: StateFlow<ArticleUiState>
        get() = TODO("Not yet implemented")

    override fun updateState(function: (ArticleUiState) -> ArticleUiState) {
        TODO("Not yet implemented")
    }

    override fun receiveEvent(event: ArticleUiEvent) {
        TODO("Not yet implemented")
    }
}
