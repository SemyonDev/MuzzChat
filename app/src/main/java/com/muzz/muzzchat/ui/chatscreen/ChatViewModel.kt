package com.muzz.muzzchat.ui.chatscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzz.muzzchat.entities.ChatMessage
import com.muzz.muzzchat.storage.convertors.toChatMessageEntity
import com.muzz.muzzchat.storage.entities.ChatMessageEntity
import com.muzz.muzzchat.usecases.ChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatUseCase: ChatUseCase) : ViewModel() {

    var answersTextList = listOf<String>()

    private val _chatMessageList = MutableStateFlow<List<ChatMessageEntity>>(emptyList())
    val chatMessageList: StateFlow<List<ChatMessageEntity>> get() = _chatMessageList

    init {
        viewModelScope.launch {
            chatUseCase.getChatHistory().collect {
                _chatMessageList.emit(it)
            }
        }
    }

    fun sendChat(text: String) {
        viewModelScope.launch {
            chatUseCase.addMessageIntoChatHistory(
                ChatMessage(
                    message = text,
                    type = 0
                ).toChatMessageEntity()
            )
            generateAnswer()
            delay(20000)
            udateIsViewed()
        }
    }

    fun setAnswerStringList(answersTextList: List<String>) {
        this.answersTextList = answersTextList
    }

    suspend fun generateAnswer() {
        val answerInterval = (1..7).random() * 1000
        delay(answerInterval.toLong())
        chatUseCase.addMessageIntoChatHistory(
            ChatMessage(
                message = answersTextList.get(
                    (0..(answersTextList.size - 1)).random()
                ),
                type = 1
            ).toChatMessageEntity()
        )
        udateIsViewed()
    }

    suspend private fun udateIsViewed(){
        chatUseCase.updateIsViewed()
    }
}