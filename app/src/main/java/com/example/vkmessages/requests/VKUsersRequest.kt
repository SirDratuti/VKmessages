package com.example.vkmessages.requests

import com.example.vkmessages.vkobjects.VKUser
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKUsersRequest(UserIDs: IntArray = intArrayOf()) : VKRequest<List<VKUser>>("users.get") {
    init {
        if (UserIDs.isNotEmpty()) {
            addParam("user_ids", UserIDs.joinToString(","))
        }
        addParam("fields", "photo_200")
    }

    override fun parse(r: JSONObject): List<VKUser> {
        val users = r.getJSONArray("response")
        val result = ArrayList<VKUser>()
        for (i in 0 until users.length()) {
            result.add(VKUser.parse(users.getJSONObject(i)))
        }
        return result
    }
}