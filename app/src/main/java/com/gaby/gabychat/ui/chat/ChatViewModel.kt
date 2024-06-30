package com.gaby.gabychat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.gabychat.domain.GetMessageUseCase
import com.gaby.gabychat.domain.SendMessageUseCase
import com.gaby.gabychat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
) : ViewModel() {

    init {
        getMessage()
    }

    var messageList = MutableStateFlow<List<MessageModel>>(emptyList())

    fun sendMessage() {
        var msg = "Holiwi"
        sendMessageUseCase(msg)
    }

    private fun getMessage() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                Log.d("Gaby", "La info es: $it")
                messageList.value = it
            }
        }
    }
}