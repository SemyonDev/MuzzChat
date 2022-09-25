package com.muzz.muzzchat.usecases

import com.muzz.muzzchat.respositories.ChatRepository
import com.muzz.muzzchat.storage.dao.ChatMessageDao
import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatMessageDao: ChatMessageDao
): ChatRepository {
    override suspend fun getChatHistory() = chatMessageDao.getChatMessageList()
    override suspend fun addMessageIntoChatHistory(chatMessageEntity: ChatMessageEntity) = chatMessageDao.insertChatMessage(chatMessageEntity)
    override suspend fun updateIsViewed() = chatMessageDao.updateIsViewed()
}