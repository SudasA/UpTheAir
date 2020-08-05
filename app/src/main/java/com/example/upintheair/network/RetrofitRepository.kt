package com.example.upintheair.network

import com.example.upintheair.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitRepository() {

    private lateinit var userService: UserService

    fun getUserService(): UserService {
        if (!::userService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
//                .baseUrl("https://us-central1-up-in-the-air-effe3.cloudfunctions.net/app")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            userService = retrofit.create(UserService::class.java)
        }

        return userService
    }

    private fun getClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .callTimeout(30, TimeUnit.SECONDS)

        return client.build()
    }
}