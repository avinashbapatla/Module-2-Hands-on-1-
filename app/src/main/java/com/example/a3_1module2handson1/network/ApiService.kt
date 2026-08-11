package com.example.a3_1module2handson1.network

import com.example.a3_1module2handson1.model.ChatRequest
import com.example.a3_1module2handson1.model.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("chat")
    suspend fun sendMessage(
        @Body request: ChatRequest
    ): Response<ChatResponse>

}