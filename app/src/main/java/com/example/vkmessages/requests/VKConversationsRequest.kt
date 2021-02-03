package com.example.vkmessages.requests

import com.example.vkmessages.vkobjects.VKConversation
import com.example.vkmessages.vkobjects.VKMessage
import com.example.vkmessages.vkobjects.VKUser
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKConversationsRequest(count: Int = 0) :
    VKRequest<List<VKConversation>>("messages.getConversations") {
    init {
        addParam("count", count)
    }

    override fun parse(r: JSONObject): List<VKConversation> {
        val conversations = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<VKConversation>()
        for (i in 0 until conversations.length()) {
            val currentItem = conversations.getJSONObject(i)
            result.add(
                VKConversation(
                    (VKConversation.parse(currentItem.getJSONObject("conversation"))),
                    (VKMessage.parse(currentItem.getJSONObject("last_message")))
                )
            )
        }
        return result
    }
}