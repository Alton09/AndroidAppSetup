package com.johnqualls.android.appsetup.articles.data

import com.johnqualls.android.appsetup.articles.data.local.ArticleDao
import com.johnqualls.android.appsetup.articles.data.remote.ArticleService
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val articleService: ArticleService,
) {

    suspend fun getArticles() = articleService.getArticles().articles
}
