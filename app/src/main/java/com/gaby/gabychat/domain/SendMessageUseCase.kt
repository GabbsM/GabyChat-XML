package com.gaby.gabychat.domain

import com.gaby.gabychat.data.network.FirebaseChatService
import com.gaby.gabychat.data.network.dto.MessageDto
import com.gaby.gabychat.data.network.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(val firebaseChatService: FirebaseChatService){

    operator fun invoke(msg:String){

        val currentMsg = msg

        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val userDto = UserDto("Prueba",false)

        val messageDto = MessageDto(
            msg = msg,
            hour = "$hour:$min",
            date = "$day/$month/$year",
            user = userDto
        )

        firebaseChatService.sendMsgToFirebase(messageDto)
    }
}