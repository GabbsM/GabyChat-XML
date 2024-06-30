package com.gaby.gabychat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaby.gabychat.R
import com.gaby.gabychat.databinding.FragmentChatBinding
import com.gaby.gabychat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewmodel by viewModels<ChatViewModel> ()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater,container,false)
        binding.ivBack.setOnClickListener {
            viewmodel.logout{findNavController().navigate(R.id.action_back)}

        }


        setupUI()
        binding.btnSendMsg.setOnClickListener {
            val msg = binding.etChat.text.toString()
            if(msg.isNotEmpty()){
                viewmodel.sendMessage(msg)
            }
            binding.etChat.text.clear()
          }
        return binding.root
    }

    private fun setupUI() {
        setUpMessage()
        subscribeToMessage()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        binding.tvTitle.text = viewmodel.name
    }

    private fun setUpMessage() {

        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMsg.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessage(){
        lifecycleScope.launch {
            viewmodel.messageList.collect{
                setUpToolbar()
                chatAdapter.updateList(it.toMutableList(),viewmodel.name)
                binding.rvMsg.scrollToPosition(chatAdapter.messageList.size - 1)
            }
        }
    }

}