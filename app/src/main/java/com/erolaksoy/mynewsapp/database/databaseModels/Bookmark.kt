package com.erolaksoy.mynewsapp.database.databaseModels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.erolaksoy.mynewsapp.models.Article

//@Entity(tableName = "bookmark")
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    var bookmarkId: Long = 0L,
    val articleId: String,
) {
}

data class ArticlesAndBookmarks(
    @Embedded val bookmark : Bookmark,
    @Relation(
        parentColumn = "articleId",
        entityColumn = "url")
    val article: List<ArticleDb>,
) {

}