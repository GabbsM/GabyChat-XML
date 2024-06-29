package com.gaby.gabychat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gaby.gabychat.R
import com.gaby.gabychat.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewmodel by viewModels<ChatViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater,container,false)
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_back)
        }

        binding.btnSendMsg.setOnClickListener { viewmodel.sendMessage() }
        return binding.root
    }

}