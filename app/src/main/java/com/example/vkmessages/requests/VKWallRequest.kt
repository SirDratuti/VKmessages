package com.example.vkmessages.requests

import com.example.vkmessages.vkobjects.VKPost
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKWallRequest(count: Int = 1) : VKRequest<List<VKPost>>("newsfeed.get") {
    init {
        addParam("count", count)
        addParam("filters", "post")
    }

    override fun parse(r: JSONObject): List<VKPost> {
        val items = r.getJSONObject("response").getJSONArray("items")
        val groups = r.getJSONObject("response").getJSONArray("groups")
        val result = ArrayList<VKPost>()
        for (i in 0 until groups.length()) {
            try {
                val item = items.getJSONObject(i).getJSONArray("attachments").getJSONObject(0)
                    .getJSONObject("photo")
                val pub = groups.getJSONObject(i)
                result.add(
                    VKPost(
                        0,
                        item.getJSONArray("sizes").getJSONObject(8).optString("url"),
                        pub.optString("photo_200"),
                        pub.optString("name"),
                        items.getJSONObject(i).optString("text")
                    )
                )
            } catch (e: Exception) {

            }

        }
        return result
    }
}