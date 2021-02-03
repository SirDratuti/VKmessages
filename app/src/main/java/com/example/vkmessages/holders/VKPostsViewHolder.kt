package com.example.vkmessages.holders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.vkobjects.VKPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.friend_item.view.avatar
import kotlinx.android.synthetic.main.post_item.view.*

class VKPostsViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    @SuppressLint("SetTextI18n")
    fun bind(user: VKPost) {
        Picasso.get().load(user.avatarUrl).into(root.avatar)
        Picasso.get().load(user.pictureUrl).into(root.post_picture)
        with(root) {
            post_text.text = user.text
            public_name.text = user.publicName
        }
    }
}