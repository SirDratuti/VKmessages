package com.example.vkmessages.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkmessages.R
import com.example.vkmessages.adapters.VKConversationAdapter
import com.example.vkmessages.navigationadvancedsample.navigate
import com.example.vkmessages.requests.VKConversationsRequest
import com.example.vkmessages.vkobjects.VKConversation
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.dialogues_fragment.*

class DialoguesFragment : Fragment() {

    var conversationList: List<VKConversation> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        VK.execute(VKConversationsRequest(20), object : VKApiCallback<List<VKConversation>> {
            override fun success(result: List<VKConversation>) {
                conversationList = result
                val viewManager = LinearLayoutManager(context)
                conversationView.apply {
                    layoutManager = viewManager
                    adapter = VKConversationAdapter(conversationList) {
                        navigate(DialoguesFragmentDirections.actionDialoguesFragmentToDialogFragment())
                    }
                }
            }

            override fun fail(error: Exception) {
            }

        })

        return inflater.inflate(R.layout.dialogues_fragment, container, false)
    }

}