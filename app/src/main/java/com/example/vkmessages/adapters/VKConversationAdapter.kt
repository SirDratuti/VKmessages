package com.example.vkmessages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.R
import com.example.vkmessages.holders.VKConversationViewHolder
import com.example.vkmessages.vkobjects.VKConversation

class VKConversationAdapter(
    private val users: List<VKConversation>,
) : RecyclerView.Adapter<VKConversationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : VKConversationViewHolder {
        return VKConversationViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.conversation_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VKConversationViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount() = users.size
}