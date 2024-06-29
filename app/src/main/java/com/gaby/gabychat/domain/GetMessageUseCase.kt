package com.gaby.gabychat.domain

import com.gaby.gabychat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService){

    operator fun invoke(){
        val response = firebaseChatService.getMessage()
    }
}