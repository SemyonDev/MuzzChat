package com.muzz.muzzchat.storage.convertors

import com.muzz.muzzchat.entities.ChatMessage
import com.muzz.muzzchat.storage.entities.ChatMessageEntity


fun ChatMessage.toChatMessageEntity() = ChatMessageEntity(
    id = this.id,
    message = this.message,
    type = this.type,
    timestamp = System.currentTimeMillis(),
    isViewed = this.isViewed,
)

fun ChatMessageEntity.toChatMessage() = ChatMessage(
    id = this.id,
    message = this.message,
    type = this.type,
    timestamp = this.timestamp,
    isViewed = this.isViewed,
)

fun List<ChatMessage>.toChatMessageEntityList() = this.map {
    it.toChatMessageEntity()
}

fun List<ChatMessageEntity>.toChatMessageList() = this.map {
    it.toChatMessage()
}
