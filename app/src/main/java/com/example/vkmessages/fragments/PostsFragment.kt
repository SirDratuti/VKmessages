package com.example.vkmessages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkmessages.R
import com.example.vkmessages.adapters.VKPostsAdapter
import com.example.vkmessages.requests.VKWallRequest
import com.example.vkmessages.vkobjects.VKPost
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.posts_fragment.view.*


class PostsFragment : Fragment() {

    private var postsList: ArrayList<VKPost> = arrayListOf()

    companion object {
        var lastPostTime: String = "empty"
    }

    lateinit var viewManager: LinearLayoutManager
    private lateinit var postAdapter: VKPostsAdapter
    var loading = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.posts_fragment, container, false)
        initialize(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
    }

    private fun initialize(view: View) {
        viewManager = LinearLayoutManager(context)
        postAdapter = VKPostsAdapter(postsList)
        view.postsView.apply {
            layoutManager = viewManager
            adapter = postAdapter
        }

        view.postsView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = viewManager.childCount
                    val totalItemCount = viewManager.itemCount
                    val pastVisibleItems = viewManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        loading = false
                        println("/n/n/n/n/n/n")
                        println(lastPostTime)
                        getPosts(10, lastPostTime)
                    }
                }
            }
        })

        getPosts(20, lastPostTime)
    }

    fun addPosts(list: List<VKPost>) {
        for (i in list) {
            postsList.add(i)
        }
        postAdapter.notifyDataSetChanged()
    }

    private fun getPosts(count: Int = 1, next_from: String = "empty") {
        Toast.makeText(context, next_from, Toast.LENGTH_SHORT).show()
        VK.execute(VKWallRequest(count, next_from), object : VKApiCallback<List<VKPost>> {
            override fun success(result: List<VKPost>) {
                addPosts(result)
            }

            override fun fail(error: Exception) {
                println(error)
            }

        })
    }

}