package com.example.a3_1module2handson1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_1module2handson1.R
import com.example.a3_1module2handson1.model.ChatMessage

class ChatAdapter(
    private val messageList: MutableList<ChatMessage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_BOT = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].isUser)
            VIEW_TYPE_USER
        else
            VIEW_TYPE_BOT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_USER) {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_message, parent, false)

            UserViewHolder(view)

        } else {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bot_message, parent, false)

            BotViewHolder(view)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val message = messageList[position]

        if (holder is UserViewHolder) {

            holder.txtUserMessage.text = message.message

        } else if (holder is BotViewHolder) {

            holder.txtBotMessage.text = message.message

        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addMessage(chatMessage: ChatMessage) {

        messageList.add(chatMessage)

        notifyItemInserted(messageList.size - 1)

    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtUserMessage: TextView =
            itemView.findViewById(R.id.tvMessage)

    }

    class BotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtBotMessage: TextView =
            itemView.findViewById(R.id.tvMessage)

    }

}