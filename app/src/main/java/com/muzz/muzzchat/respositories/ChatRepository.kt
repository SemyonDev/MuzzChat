package com.muzz.muzzchat.respositories

import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getChatHistory(): Flow<List<ChatMessageEntity>>
    suspend fun addMessageIntoChatHistory(chatMessageEntity: ChatMessageEntity): Long
    suspend fun updateIsViewed(): Int
}