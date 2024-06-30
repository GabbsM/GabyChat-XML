package com.gaby.gabychat.domain.model

import com.gaby.gabychat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService){

    operator fun invoke() = firebaseChatService.getMessage()


}