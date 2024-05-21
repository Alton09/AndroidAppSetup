package com.johnqualls.android.appsetup.articles.ui

data class ArticleUiState(
    val isLoading: Boolean = true,
    val articles: List<Article> = emptyList(),
    // TODO Add error states
)
