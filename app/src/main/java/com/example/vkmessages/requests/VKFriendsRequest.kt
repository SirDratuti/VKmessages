package com.example.vkmessages.requests

import com.example.vkmessages.vkobjects.VKUser
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import java.lang.Exception

class VKFriendsRequest(uid: Int = 0) : VKRequest<List<VKUser>>("friends.get") {
    init {
        if (uid != 0) {
            addParam("user_id", uid)
        }
        addParam("fields", "photo_200,city")
    }

    override fun parse(r: JSONObject): List<VKUser> {
        val users = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<VKUser>()
        for (i in 0 until users.length()) {
            val json = users.getJSONObject(i)
            var city = ""
            try {
                city = json.getJSONObject("city").optString("title", "")
            } catch (e: Exception) {
            }
            result.add(
                VKUser(
                    id = json.optInt("id", 0),
                    firstName = json.optString("first_name", ""),
                    lastName = json.optString("last_name", ""),
                    photo = json.optString("photo_200", ""),
                    deactivated = json.optBoolean("deactivated", false),
                    city = city
                )
            )
        }
        return result
    }
}