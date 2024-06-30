package com.gaby.gabychat.domain.model

import com.gaby.gabychat.data.network.FirebaseChatService
import com.gaby.gabychat.data.network.dto.MessageDto
import com.gaby.gabychat.data.network.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(val firebaseChatService: FirebaseChatService){

    operator fun invoke(msg:String, nickname:String){


        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY) + 2
        val min = calendar.get(Calendar.MINUTE)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val userDto = UserDto(nickname,false)

        val messageDto = MessageDto(
            msg = msg,
            hour = "$hour:$min",
            date = "$day/$month/$year",
            user = userDto
        )

        firebaseChatService.sendMsgToFirebase(messageDto)
    }
}