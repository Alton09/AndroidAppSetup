package com.johnqualls.android.appsetup.articles.ui

import androidx.compose.runtime.Composable
import com.johnqualls.android.appsetup.Greeting
import com.johnqualls.android.appsetup.core.ui.Screen

class ArticleScreen(override val presenter: ArticleViewModel) : Screen<ArticleUiEvent, ArticleUiState> {
    @Composable
    override fun Render(state: ArticleUiState) {
        Greeting(name = "Hello World!")
    }
}
