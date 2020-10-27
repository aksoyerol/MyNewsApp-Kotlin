package com.erolaksoy.mynewsapp.database.databaseModels

import androidx.lifecycle.Transformations.map
import androidx.room.Entity
import com.erolaksoy.mynewsapp.models.Source

@Entity(tableName = "sourceDb")
data class SourceDb(
    val id: String?,
    val name: String?,
)


fun SourceDb.asSource() = Source(id = id, name = name)