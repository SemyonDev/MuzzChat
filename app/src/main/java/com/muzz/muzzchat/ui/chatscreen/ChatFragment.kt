package com.muzz.muzzchat.ui.chatscreen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muzz.muzzchat.R
import com.muzz.muzzchat.base.BaseFragment
import com.muzz.muzzchat.databinding.FragmentChatBinding
import com.muzz.muzzchat.entities.ChatMessage
import com.muzz.muzzchat.storage.convertors.toChatMessageList
import com.muzz.muzzchat.ui.chatscreen.adapters.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>() {

    private val viewModel: ChatViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChatBinding.inflate(layoutInflater, container, false)

    override fun init() {
        val answersTextList = resources.getStringArray(R.array.answers_array)
        viewModel.setAnswerStringList(answersTextList.toList())
        handleOnBackPressed()
        initRV()
    }

    private fun handleOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    private fun initRV() {
        val chatAdapter = ChatAdapter()
        val chatLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        chatLayoutManager.stackFromEnd = true
        binding.fragmentChatRv.apply {
            adapter = chatAdapter
            layoutManager = chatLayoutManager

        }

        binding.fragmentChatSendBtn.setOnClickListener {
            viewModel.sendChat(binding.fragmentChatTxt.text.toString())
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.chatMessageList.collect { list ->
                        chatAdapter.setChatMessageList(list.toChatMessageList())
                        binding.fragmentChatRv.smoothScrollToPosition(chatAdapter.itemCount + 1)
                    }
                }
            }
        }
    }

}