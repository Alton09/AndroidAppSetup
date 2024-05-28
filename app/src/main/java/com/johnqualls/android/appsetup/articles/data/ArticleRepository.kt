package com.johnqualls.android.appsetup.articles.data

import com.johnqualls.android.appsetup.articles.data.networking.ArticleService
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val articleService: ArticleService) {

    suspend fun getArticles() = articleService.getArticles().articles
}
