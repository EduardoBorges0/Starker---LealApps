package com.app.starker.data.model

data class UserModel(
    val username: String = "",
    val email: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
