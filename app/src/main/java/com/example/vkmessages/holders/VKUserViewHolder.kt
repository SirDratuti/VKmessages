package com.example.vkmessages.holders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.vkobjects.VKUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.friend_item.view.*

class VKUserViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    @SuppressLint("SetTextI18n")
    fun bind(user: VKUser) {
        Picasso.get().load(user.photo).into(root.avatar)
        with(root) {
            name.text = "${user.firstName} ${user.lastName}"
        }
    }
}
