package com.muzz.muzzchat.ui.chatscreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muzz.muzzchat.databinding.ItemMessageMeBinding
import com.muzz.muzzchat.databinding.ItemMessageOtherBinding
import com.muzz.muzzchat.entities.ChatMessage

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_MY_MESSAGE = 0
        private val VIEW_TYPE_OTHER_MESSAGE = 1
    }

    private var messageList = mutableListOf<ChatMessage>()

    fun loadMessages(messages: List<ChatMessage>) {
        messageList.clear()
        messageList.addAll(messages)
        notifyDataSetChanged()
    }

    fun addFirst(message: ChatMessage) {
        messageList.add(0, message)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MY_MESSAGE -> {
                MyMessageHolder(
                    ItemMessageMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            VIEW_TYPE_OTHER_MESSAGE -> {
                OtherMessageHolder(
                    ItemMessageOtherBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> OtherMessageHolder(
                ItemMessageOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    inner class MyMessageHolder(val binding: ItemMessageMeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage) {
            with(binding) {
                itemMessageMeHeaderTimeTxt.visibility = View.GONE
                itemMessageMeTxt.text = chatMessage.message
            }
        }
    }

    inner class OtherMessageHolder(val binding: ItemMessageOtherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage) {
            with(binding) {
                itemMessageOtherTxt.text = chatMessage.message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return messageList.get(position).type
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyMessageHolder -> holder.bind(messageList.get(position))
            is OtherMessageHolder -> holder.bind(messageList.get(position))
        }
    }
}
