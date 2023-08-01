package com.example.inseminator.core.data.api.response

import com.example.inseminator.core.data.api.response.item.LoginItem

data class LoginRespon(
    val status : String,
    val code : Int,
    val message : String,
    val data : List <LoginItem>
)
