package com.example.vkmessages.holders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.vkobjects.VKConversation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.conversation_item.view.*


class VKConversationViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
    @SuppressLint("SetTextI18n")
    fun bind(user: VKConversation) {
        with(root) {
            val mess: String = user.last?.lastText ?: ""
            text.text = mess.subSequence(0, min(mess.length, 80))
            from_id.text = (user.from?.firstName ?: "") + "  " + (user.from?.lastName ?: "")
            var photo = user.from?.photo
            if(photo == null || photo == ""){
                photo = "https://sun9-62.userapi.com/s/v1/ig1/maT2N8Q9l9fLa1pGhXbShjJsSLiZeDi4dSXSI3tE43jTXTDkF6lGaz6BJS1C9MCZI9mEZaAA.jpg?size=50x0&quality=96&crop=372,0,1288,1288&ava=1"
            }
            Picasso.get().load(photo).into(root.avatar)
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