package com.example.upintheair.entity

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)