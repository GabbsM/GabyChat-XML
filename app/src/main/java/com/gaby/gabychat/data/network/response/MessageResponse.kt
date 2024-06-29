package com.gaby.gabychat.data.network.response

import com.gaby.gabychat.data.network.dto.UserDto
import com.gaby.gabychat.domain.model.MessageModel
import com.gaby.gabychat.domain.model.UserModel

data class MessageResponse(

    val msg:String,
    val hour: String,
    val date: String,
    val user: UserResponse
) {

    fun toDomain():MessageModel{
        return MessageModel(
            msg = msg,
            hour = hour,
            date = date,
            user = UserModel(user = user.userName, admin = user.admin)
        )
    }

}

data class UserResponse(

    val userName:String,
    val admin: Boolean
)
