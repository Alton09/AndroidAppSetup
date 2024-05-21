package com.johnqualls.android.appsetup.articles.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.johnqualls.android.appsetup.core.ui.Presenter
import com.johnqualls.android.appsetup.core.ui.Screen

class ArticleScreen(override val presenter: Presenter<ArticleUiEvent, ArticleUiState>) :
    Screen<ArticleUiEvent, ArticleUiState> {
    @Composable
    override fun Render(state: ArticleUiState) {
        if (state.isLoading) {
            LoadingIndicator()
        } else {
            Content(state.articles)
        }
    }

    @Composable
    private fun LoadingIndicator() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun Content(articles: List<Article>) {
        if (articles.isNotEmpty()) {
            LazyColumn {
            }
        } else {
            // TODO Show empty article state
        }
    }
}
