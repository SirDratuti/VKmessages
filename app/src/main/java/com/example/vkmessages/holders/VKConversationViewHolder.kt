package com.example.vkmessages.holders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.vkobjects.VKConversation
import kotlinx.android.synthetic.main.conversation_item.view.*
import kotlinx.android.synthetic.main.friend_item.view.*


class VKConversationViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    @SuppressLint("SetTextI18n")
    fun bind(user: VKConversation) {
        with(root) {
            text.text = user.last?.lastText ?: ""
            from_id.text = user.id.toString()
        }
    }
}