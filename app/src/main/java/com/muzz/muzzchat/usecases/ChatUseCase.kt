package com.muzz.muzzchat.usecases

import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatUseCase {
    fun getChatHistory(): Flow<List<ChatMessageEntity>>
    suspend fun addMessageIntoChatHistory(chatMessageEntity: ChatMessageEntity): Long
}