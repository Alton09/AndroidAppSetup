package com.johnqualls.android.appsetup.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johnqualls.android.appsetup.articles.data.local.ArticleDao
import com.johnqualls.android.appsetup.articles.data.local.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
