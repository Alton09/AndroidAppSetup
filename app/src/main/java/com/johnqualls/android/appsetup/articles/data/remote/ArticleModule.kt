package com.johnqualls.android.appsetup.articles.data.remote

import com.johnqualls.android.appsetup.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ArticleModule {

    @Provides
    fun providesArticleService(retrofit: Retrofit): ArticleService =
        retrofit.create(ArticleService::class.java)

    @Provides
    fun providesArticleDao(appDatabase: AppDatabase) = appDatabase.articleDao()
}
