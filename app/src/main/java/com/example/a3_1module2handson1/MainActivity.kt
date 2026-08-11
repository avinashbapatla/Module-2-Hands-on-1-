package com.example.a3_1module2handson1

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_1module2handson1.adapter.ChatAdapter
import com.example.a3_1module2handson1.model.ChatMessage
import com.example.a3_1module2handson1.viewmodel.ChatViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editMessage: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var progressBar: ProgressBar

    private lateinit var chatAdapter: ChatAdapter

    private val messageList = mutableListOf<ChatMessage>()

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.chatRecyclerView)
        editMessage = findViewById(R.id.etMessage)
        sendButton = findViewById(R.id.btnSend)
        progressBar = findViewById(R.id.progressBar)

        chatAdapter = ChatAdapter(messageList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter

        observeViewModel()

        sendButton.setOnClickListener {

            val userMessage = editMessage.text.toString().trim()

            if (userMessage.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter a message",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Add user message
            chatAdapter.addMessage(
                ChatMessage(
                    message = userMessage,
                    isUser = true,
                    timestamp = getCurrentTime()
                )
            )

            recyclerView.scrollToPosition(chatAdapter.itemCount - 1)

            editMessage.text.clear()

            // Send to backend
            viewModel.sendMessage(userMessage)
        }
    }

    private fun observeViewModel() {

        viewModel.reply.observe(this) { reply ->

            chatAdapter.addMessage(
                ChatMessage(
                    message = reply,
                    isUser = false,
                    timestamp = getCurrentTime()
                )
            )

            recyclerView.scrollToPosition(chatAdapter.itemCount - 1)

        }

        viewModel.isLoading.observe(this) { loading ->

            progressBar.visibility =
                if (loading)
                    ProgressBar.VISIBLE
                else
                    ProgressBar.GONE

        }
    }

    private fun getCurrentTime(): String {

        val sdf = SimpleDateFormat(
            "hh:mm a",
            Locale.getDefault()
        )

        return sdf.format(Date())
    }
}