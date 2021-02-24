package com.example.vkmessages.requests

import com.example.vkmessages.comparators.VKGroupComparator
import com.example.vkmessages.containers.ArraySet
import com.example.vkmessages.fragments.PostsFragment
import com.example.vkmessages.vkobjects.VKGroup
import com.example.vkmessages.vkobjects.VKPost
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKWallRequest(count: Int = 1, next_from : String = "empty") : VKRequest<List<VKPost>>("newsfeed.get") {
    init {
        addParam("count", count)
        addParam("filters", "post")
        if(next_from != "empty"){
            addParam("start_from",next_from)
        }
    }

    override fun parse(r: JSONObject): List<VKPost> {
        PostsFragment.lastPostTime = r.getJSONObject("response").optString("next_from")

        val items = r.getJSONObject("response").getJSONArray("items")
        val groups = r.getJSONObject("response").getJSONArray("groups")
        val postList = ArrayList<VKPost>()
        val groupList = ArrayList<VKGroup>()
        for (i in 0 until groups.length()) {
            try {
                val pub = groups.getJSONObject(i)
                val groupName = pub.optString("name")
                val groupId = pub.optInt("id")
                val groupAvatarUrl = pub.optString("photo_200")
                val postGroup = VKGroup(groupId, groupName, groupAvatarUrl)
                groupList.add(postGroup)
            } catch (e: Exception) {
            }
        }

        for (i in 0 until items.length()) {
            try {
                val item = items.getJSONObject(i).getJSONArray("attachments").getJSONObject(0)
                    .getJSONObject("photo")
                val postText = items.getJSONObject(i).optString("text")
                val postId = items.getJSONObject(i).optInt("source_id") * -1
                val listOfPhotos = ArrayList<String>()
                listOfPhotos.add(
                    item.getJSONArray("sizes").getJSONObject(7)
                        .optString("url")
                )
                val date = items.getJSONObject(i).optInt("date").toLong()
                val post =
                    VKPost(pictures = listOfPhotos, groupId = postId, text = postText, time = date)

                postList.add(post)
            } catch (e: Exception) {
            }
        }

        for (i in postList) {
            i.group = find(i.groupId, groupList)
        }
        return postList
    }

    private fun find(id: Int, groupList: List<VKGroup>): VKGroup? {
        for (i in groupList) {
            if (i.id == id) {
                return i
            }
        }
        return null
    }
}