package com.gaby.gabychat.domain.model

import com.gaby.gabychat.data.network.response.UserResponse

data class MessageModel (

    val msg:String,
    val hour: String,
    val date: String,
    val user: UserModel
)

data class UserModel(

    val user:String,
    val admin: Boolean
)

