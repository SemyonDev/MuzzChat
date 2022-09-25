package com.muzz.muzzchat.ui.homescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muzz.muzzchat.R
import com.muzz.muzzchat.base.BaseFragment
import com.muzz.muzzchat.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =    FragmentHomeBinding.inflate(layoutInflater, container, false)


    override fun init() {
        binding.fragmentHomeChatBtn.setOnClickListener {
            navigateToChatScreen()
        }
    }

    private fun navigateToChatScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
    }


}