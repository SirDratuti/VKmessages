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

    var previousTotal = 0
    var loading = true
    val visibleThreshold = 10
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var start = false


    companion object {
        var lastPostTime: String = "empty"
    }

    lateinit var viewManager: LinearLayoutManager
    private lateinit var postAdapter: VKPostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.posts_fragment, container, false)
        initialize(view)
        return view
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
                    visibleItemCount = recyclerView.childCount
                    totalItemCount = viewManager.itemCount
                    firstVisibleItem = viewManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false
                            previousTotal = totalItemCount
                        }
                    }

                    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold) && start) {
                        val initialSize = postsList.size
                        getPosts(10, lastPostTime)
                        val updatedSize = postsList.size
                        recyclerView.post {
                            postAdapter.notifyItemRangeInserted(
                                initialSize,
                                updatedSize
                            )
                        }
                        loading = true
                    }
                }
            }
        })

        getPosts(10, lastPostTime)
    }

    fun addPosts(list: List<VKPost>) {
        for (i in list) {
            postsList.add(i)
        }
    }

    private fun getPosts(count: Int = 1, next_from: String = "empty") {
        start = true
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