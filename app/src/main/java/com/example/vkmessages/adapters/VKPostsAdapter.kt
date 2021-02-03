package com.example.vkmessages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.R
import com.example.vkmessages.holders.VKPostsViewHolder
import com.example.vkmessages.vkobjects.VKPost

class VKPostsAdapter(
    private val users: List<VKPost>,
) : RecyclerView.Adapter<VKPostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : VKPostsViewHolder {
        return VKPostsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VKPostsViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount() = users.size

}