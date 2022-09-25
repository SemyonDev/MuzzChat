package com.muzz.muzzchat.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao

interface ChatMessageDao {

    @Query("SELECT * FROM ChatMessageList")
    fun getChatMessageList(): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatMessageList(vararg chatMessageList: ChatMessageEntity): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatMessage(chatMessage: ChatMessageEntity): Long

}
