package com.muzz.muzzchat.storage.convertors

import com.muzz.muzzchat.entities.ChatMessage
import com.muzz.muzzchat.storage.entities.ChatMessageEntity


fun ChatMessage.toChatMessageEntity() = ChatMessageEntity(
    id = this.id,
    message = this.message,
    type = this.type,
    timestamp = System.currentTimeMillis(),
)

fun ChatMessageEntity.toChatMessage() = ChatMessage(
    id = this.id,
    message = this.message,
    type = this.type,
    timestamp = this.timestamp,
)

fun List<ChatMessage>.toChatMessageEntityList() = this.map {
    it.toChatMessageEntity()
}

fun List<ChatMessageEntity>.toChatMessageList() = this.map {
    it.toChatMessage()
}
