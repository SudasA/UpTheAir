package com.example.upintheair.network

import com.example.upintheair.entity.UserRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("/users")
    fun postUser(
        @Body request: UserRequest?
    ): Call<ResponseBody>
}