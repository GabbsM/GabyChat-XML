package com.gaby.gabychat.data.network.dto

data class MessageDto (

    val msg:String,
    val hour: String,
    val date: String,
    val user: UserDto

)

data class UserDto(

    val user:String,
    val admin: Boolean
)