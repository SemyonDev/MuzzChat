package com.muzz.muzzchat.storage.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChatMessageList")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @NonNull @ColumnInfo(name = "type") val type: Int = 0,
    @NonNull @ColumnInfo(name = "message") val message: String? = "",
    @NonNull @ColumnInfo(name = "isViewed") val isViewed: Boolean = false,
    @NonNull @ColumnInfo(name = "timestamp") val timestamp: Long? = null,
)
