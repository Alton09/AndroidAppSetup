package com.johnqualls.android.appsetup.articles.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<ArticleEntity>
}
