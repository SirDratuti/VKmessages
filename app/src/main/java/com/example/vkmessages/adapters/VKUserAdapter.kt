package com.example.vkmessages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.R
import com.example.vkmessages.vkobjects.VKUser
import com.example.vkmessages.holders.VKUserViewHolder

class VKUserAdapter(
    private val users: List<VKUser>,
) : RecyclerView.Adapter<VKUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : VKUserViewHolder {
        return VKUserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.friend_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VKUserViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount() = users.size

}