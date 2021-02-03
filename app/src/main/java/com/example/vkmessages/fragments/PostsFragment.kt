package com.example.vkmessages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkmessages.R
import com.example.vkmessages.adapters.VKPostsAdapter
import com.example.vkmessages.adapters.VKUserAdapter
import com.example.vkmessages.requests.VKFriendsRequest
import com.example.vkmessages.requests.VKWallRequest
import com.example.vkmessages.vkobjects.VKPost
import com.example.vkmessages.vkobjects.VKUser
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.friends_fragment.*

class PostsFragment : Fragment() {

    var postsList: List<VKPost> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        VK.execute(VKWallRequest(4), object : VKApiCallback<List<VKPost>> {
            override fun success(result: List<VKPost>) {
                postsList = result
                println(postsList.size)
                val viewManager = LinearLayoutManager(context)
                friendsView.apply {
                    layoutManager = viewManager
                    adapter = VKPostsAdapter(postsList)
                }
            }

            override fun fail(error: Exception) {
            }

        })

        return inflater.inflate(R.layout.friends_fragment, container, false)
    }

}