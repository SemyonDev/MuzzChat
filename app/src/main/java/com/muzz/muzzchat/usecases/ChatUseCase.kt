package com.muzz.muzzchat.usecases

import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatUseCase {
    suspend fun getChatHistory(): Flow<List<ChatMessageEntity>>
    suspend fun addMessageIntoChatHistory(chatMessageEntity: ChatMessageEntity): Long
}