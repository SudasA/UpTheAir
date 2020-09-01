package com.example.upintheair.network

import com.example.upintheair.entity.UserRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("/app/api/create_user")
    suspend fun postUser(
        @Body request: UserRequest
    ): ResponseBody

    @GET("/app/api/users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): UserRequest
}