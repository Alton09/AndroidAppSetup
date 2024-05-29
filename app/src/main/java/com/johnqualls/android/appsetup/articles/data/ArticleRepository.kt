package com.johnqualls.android.appsetup.articles.data

import android.content.Context
import android.net.ConnectivityManager
import com.johnqualls.android.appsetup.articles.data.local.ArticleDao
import com.johnqualls.android.appsetup.articles.data.local.ArticleEntity
import com.johnqualls.android.appsetup.articles.data.remote.ArticleService
import com.johnqualls.android.appsetup.articles.ui.Article
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val articleDao: ArticleDao,
    private val articleService: ArticleService,
) {

    suspend fun observeArticles(): Flow<List<Article>> {
        if (isNetworkAvailable()) {
            updateDatabaseFromNetwork()
        }
        return observeArticlesFromDatabase()
    }

    private suspend fun updateDatabaseFromNetwork() {
        val articleEntities = articleService.getArticles().articles.map {
            ArticleEntity(
                author = it.author,
                title = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
            )
        }
        articleDao.deleteAllArticles()
        articleDao.insertArticles(articleEntities)
    }

    private fun observeArticlesFromDatabase() = articleDao.observeArticles().map { articleEntities ->
        articleEntities.map {
            Article(
                author = it.author,
                title = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
            )
        }
    }

    private fun isNetworkAvailable(): Boolean {
        // TODO Have ConnectivityManager be a dependency of this class
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return activeNetwork != null && connectivityManager.getNetworkCapabilities(activeNetwork) != null
    }
}
