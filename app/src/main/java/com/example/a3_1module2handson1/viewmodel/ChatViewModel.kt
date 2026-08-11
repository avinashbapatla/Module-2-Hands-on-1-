package com.example.a3_1module2handson1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3_1module2handson1.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()

    // Bot Reply
    private val _reply = MutableLiveData<String>()
    val reply: LiveData<String> = _reply

    // Loading Indicator
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun sendMessage(message: String) {

        if (message.isBlank()) return

        _isLoading.value = true

        viewModelScope.launch {

            try {

                val response = repository.sendMessage(message)

                if (response.isSuccessful && response.body() != null) {

                    _reply.value = response.body()!!.reply

                } else {

                    _reply.value = "Server Error"

                }

            } catch (e: Exception) {

                _reply.value = "Unable to connect to server."

            }

            _isLoading.value = false

        }

    }
}