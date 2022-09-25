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
import com.muzz.muzzchat.helpers.DateTimeHelper.Companion.getDate

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_MY_MESSAGE = 0
        private val VIEW_TYPE_OTHER_MESSAGE = 1
    }

    var startTime = 0L
    var interval = 100000

    private var messageList = mutableListOf<ChatMessage>()

    fun setChatMessageList(newMessageList: List<ChatMessage>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffUtilCallback(messageList, newMessageList))
        messageList.clear()
        messageList.addAll(newMessageList)
        diffResult.dispatchUpdatesTo(this)
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
                showTimeHeader(chatMessage)
                if (chatMessage.isShowTimeStamp) {
                    itemMessageMeHeaderTimeTxt.visibility = View.VISIBLE
                    itemMessageMeHeaderTimeTxt.text =  getDate(startTime, "EEEE HH:mm")
                }
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
        if (position == 0) {
            startTime = 0L
        }
        when (holder) {
            is MyMessageHolder -> holder.bind(messageList.get(position))
            is OtherMessageHolder -> holder.bind(messageList.get(position))
        }
    }

    inner class UserDiffUtilCallback(
        private val oldList: List<ChatMessage>,
        private val newList: List<ChatMessage>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when {
                oldList[oldItemPosition].id == newList[newItemPosition].id -> true
                else -> false
            }
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }

    private fun showTimeHeader(chatMessage: ChatMessage) {
        chatMessage.timestamp?.let {
            if ((startTime + interval) < it) {
                startTime = it
                chatMessage.isShowTimeStamp = true
            }
        }

    }
}
