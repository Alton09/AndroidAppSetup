package com.johnqualls.android.appsetup.articles.data.remote

import com.johnqualls.android.appsetup.BuildConfig
import com.johnqualls.android.appsetup.articles.ui.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("everything")
    suspend fun getArticles(
        @Query("q") query: String = "android",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): ArticleResponse
}
