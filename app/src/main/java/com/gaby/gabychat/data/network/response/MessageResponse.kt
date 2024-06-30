package com.gaby.gabychat.data.network.response

import com.gaby.gabychat.data.network.dto.UserDto
import com.gaby.gabychat.domain.model.MessageModel
import com.gaby.gabychat.domain.model.UserModel

data class MessageResponse(

    val msg:String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
) {

    fun toDomain():MessageModel{
        return MessageModel(
            msg = msg.orEmpty(),
            hour = hour ?: "no date",
            date = date.orEmpty(),
            user = UserModel(user = user?.userName?: "guess", admin = user?.admin?: false)
        )
    }

}

data class UserResponse(

    val userName:String? = null,
    val admin: Boolean? = null
)
