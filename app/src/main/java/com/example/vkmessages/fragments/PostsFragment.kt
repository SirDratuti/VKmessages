package com.example.vkmessages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkmessages.R
import com.example.vkmessages.adapters.VKPostsAdapter
import com.example.vkmessages.requests.VKWallRequest
import com.example.vkmessages.vkobjects.VKPost
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.posts_fragment.*

class PostsFragment : Fragment() {

    var postsList: List<VKPost> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getPosts(20)
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPosts(20)
    }

    private fun getPosts(count: Int = 1) {
        VK.execute(VKWallRequest(count), object : VKApiCallback<List<VKPost>> {
            override fun success(result: List<VKPost>) {
                postsList = result
                println(postsList.size)
                val viewManager = LinearLayoutManager(context)
                postsView.apply {
                    layoutManager = viewManager
                    adapter = VKPostsAdapter(postsList)
                }
            }

            override fun fail(error: Exception) {
                println(error)
            }

        })
    }

}