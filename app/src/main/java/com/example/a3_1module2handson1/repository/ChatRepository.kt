package com.example.a3_1module2handson1.repository

import com.example.a3_1module2handson1.model.ChatRequest
import com.example.a3_1module2handson1.model.ChatResponse
import com.example.a3_1module2handson1.network.RetrofitClient
import retrofit2.Response

class ChatRepository {

    suspend fun sendMessage(message: String): Response<ChatResponse> {

        val request = ChatRequest(message)

        return RetrofitClient.apiService.sendMessage(request)

    }

}