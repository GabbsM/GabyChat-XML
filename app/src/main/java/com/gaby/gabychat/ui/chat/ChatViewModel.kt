package com.gaby.gabychat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.gabychat.domain.model.GetMessageUseCase
import com.gaby.gabychat.domain.model.GetUserNameUseCase
import com.gaby.gabychat.domain.model.LogoutUseCase
import com.gaby.gabychat.domain.model.SendMessageUseCase
import com.gaby.gabychat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var name = ""
    init {
        getUserName()
        getMessage()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {

            name = getUserNameUseCase()
        }

    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList:StateFlow<List<MessageModel>> = _messageList

    fun sendMessage(msg:String) {
        sendMessageUseCase(msg,name)
    }

    private fun getMessage() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                Log.d("Gaby", "La info es: $it")
                _messageList.value = it
            }
        }
    }

    fun logout(onViewFinish:() -> Unit) {
        viewModelScope.launch {
           async { logoutUseCase() }.await()
            onViewFinish()
        }
    }
}