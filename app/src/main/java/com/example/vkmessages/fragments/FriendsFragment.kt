package com.example.vkmessages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkmessages.R
import com.example.vkmessages.vkobjects.VKUser
import com.example.vkmessages.adapters.VKUserAdapter
import com.example.vkmessages.requests.VKFriendsRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.friends_fragment.*

class FriendsFragment : Fragment() {

    var friendsList : List<VKUser> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        VK.execute(VKFriendsRequest(), object : VKApiCallback<List<VKUser>> {
            override fun success(result: List<VKUser>) {
                friendsList = result.sortedWith(compareBy({it.firstName}, {it.lastName}))
                val viewManager = LinearLayoutManager(context)
                friendsView.apply {
                    layoutManager = viewManager
                    adapter = VKUserAdapter(friendsList)
                }
            }

            override fun fail(error: Exception) {
                println("SOMETHING WENT WRONG")
            }
        })

        return inflater.inflate(R.layout.friends_fragment, container, false)
    }

}