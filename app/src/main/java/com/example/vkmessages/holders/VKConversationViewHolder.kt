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
            val mess: String = user.last?.lastText ?: ""
            text.text = mess.subSequence(0, min(mess.length, 80))
            from_id.text = user.id.toString()
        }
    }

    private fun min(a: Int, b: Int): Int {
        return if (a >= b) {
            b
        } else {
            a
        }
    }
}