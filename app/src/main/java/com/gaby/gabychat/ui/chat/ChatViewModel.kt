package com.gaby.gabychat.ui.chat

import androidx.lifecycle.ViewModel
import com.gaby.gabychat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val sendMessageUseCase: SendMessageUseCase):ViewModel() {

    fun sendMessage(){
        var msg = "Holiwi"
        sendMessageUseCase(msg)
    }
}