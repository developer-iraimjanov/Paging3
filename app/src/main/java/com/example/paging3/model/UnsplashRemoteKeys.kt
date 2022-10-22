package com.example.paging3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3.util.Constants.UNSPLASH_REMOTE_KEY_TABLE

@Entity(tableName = UNSPLASH_REMOTE_KEY_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var prevPage: Int?,
    var nextPage: Int?,
)