package com.muzz.muzzchat.usecases

import com.muzz.muzzchat.respositories.ChatRepository
import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import javax.inject.Inject

class ChatUseCaseImpl @Inject constructor(private val chatRepository: ChatRepository) :
    ChatUseCase {
    override suspend fun getChatHistory() = chatRepository.getChatHistory()
    override suspend fun addMessageIntoChatHistory(chatMessageEntity: ChatMessageEntity) =
        chatRepository.addMessageIntoChatHistory(chatMessageEntity)
}