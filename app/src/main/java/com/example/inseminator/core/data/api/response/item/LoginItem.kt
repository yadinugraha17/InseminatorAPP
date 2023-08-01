package com.example.inseminator.core.data.api.response.item

data class LoginItem(
    val token : String,
    val user_id : Int,
    val state : Boolean
)
