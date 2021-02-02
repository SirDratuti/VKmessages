package com.example.vkmessages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkmessages.R
import com.example.vkmessages.adapters.VKConversationAdapter
import com.example.vkmessages.adapters.VKUserAdapter
import com.example.vkmessages.requests.VKConversationsRequest
import com.example.vkmessages.requests.VKFriendsRequest
import com.example.vkmessages.vkobjects.VKConversation
import com.example.vkmessages.vkobjects.VKUser
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.friends_fragment.*

class DialoguesFragment : Fragment(){

    var conversationList : List<VKConversation> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        VK.execute(VKConversationsRequest(20), object : VKApiCallback<List<VKConversation>> {
            override fun success(result: List<VKConversation>) {
                conversationList = result
                println("~~~~~~~~~~~~~~~~~~~~~~~~~")
                println(conversationList.size)
                println("~~~~~~~~~~~~~~~~~~~~~~~~~")
                val viewManager = LinearLayoutManager(context)
                friendsView.apply {
                    layoutManager = viewManager
                    adapter = VKConversationAdapter(conversationList)
                }
            }

            override fun fail(error: Exception) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~")
                println(error)
                println("~~~~~~~~~~~~~~~~~~~~~~~~~")
            }

        })

        return inflater.inflate(R.layout.dialogues_fragment, container, false)
    }

}