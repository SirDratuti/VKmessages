package com.example.vkmessages.requests

import com.example.vkmessages.vkobjects.VKConversation
import com.example.vkmessages.vkobjects.VKMessage
import com.example.vkmessages.vkobjects.VKUser
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKConversationsRequest(count: Int = 4) :
    VKRequest<List<VKConversation>>("messages.getConversations") {
    init {
        addParam("count", count)
        addParam("extended", "1")
    }

    override fun parse(r: JSONObject): List<VKConversation> {
        val conversations = r.getJSONObject("response").getJSONArray("items")
        val users = r.getJSONObject("response").getJSONArray("profiles")
        val groups = r.getJSONObject("response").getJSONArray("groups")

        val result = ArrayList<VKConversation>()
        val usersRes = ArrayList<VKUser>()
        for (i in 0 until conversations.length()) {
            val currentItem = conversations.getJSONObject(i)
            result.add(
                VKConversation(
                    (VKConversation.parse(currentItem.getJSONObject("conversation"))),
                    (VKMessage.parse(currentItem.getJSONObject("last_message")))
                )
            )
        }

        var userCount = 0
        var groupCount = 0
        for (i in 0 until conversations.length()) {
            when(conversations.getJSONObject(i).getJSONObject("conversation").getJSONObject("peer").optString("type")){
                "user" -> {
                    val cur = users.getJSONObject(userCount++)
                    val currentUser = VKUser(
                        id = cur.optInt("id"),
                        firstName = cur.optString("first_name"),
                        lastName = cur.optString("last_name"),
                        photo = cur.optString("photo_100")
                    )
                    usersRes.add(currentUser)
                }
                "group" -> {
                    val cur = groups.getJSONObject(groupCount++)
                    val currentUser = VKUser(
                        id = cur.optInt("id") * -1,
                        firstName = cur.optString("name"),
                        lastName = "  ",
                        photo = cur.optString("photo_100")
                    )
                    println(currentUser)
                    usersRes.add(currentUser)
                }
                "chat" -> {
                    val cur = conversations.getJSONObject(i).getJSONObject("conversation").getJSONObject("chat_settings")
                    val currentUser = VKUser(
                        id = conversations.getJSONObject(i).getJSONObject("conversation").getJSONObject("peer").optInt("id"),
                        firstName = cur.optString("title")
                    )
                    usersRes.add(currentUser)
                }
            }
        }

        for(i in userCount until users.length()){
            val cur = users.getJSONObject(i)
            val currentUser = VKUser(
                id = cur.optInt("id"),
                firstName = cur.optString("first_name"),
                lastName = cur.optString("last_name"),
                photo = cur.optString("photo_100")
            )
            usersRes.add(currentUser)
        }

        for(i in groupCount until groups.length()){
            val cur = groups.getJSONObject(i)
            val currentUser = VKUser(
                id = cur.optInt("id") * -1,
                firstName = cur.optString("name"),
                lastName = "  ",
                photo = cur.optString("photo_100")
            )
            println(currentUser)
            usersRes.add(currentUser)
        }

        for (i in result) {
            for (j in usersRes) {
                if (j.id == i.id) {
                    i.from = j
                }
            }
        }

        return result
    }
}