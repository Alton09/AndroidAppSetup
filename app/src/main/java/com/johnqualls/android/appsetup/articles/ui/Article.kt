package com.johnqualls.android.appsetup.articles.ui

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val articles: List<Article>,
)

@Serializable
data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
)
