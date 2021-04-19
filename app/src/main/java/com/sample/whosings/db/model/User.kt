package com.sample.whosings.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "score")
    var score: Int = 0,
    @ColumnInfo(name = "isCurrentUser")
    var isCurrent: Boolean = false
)
