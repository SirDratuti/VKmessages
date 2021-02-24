package com.example.vkmessages.containers

import com.example.vkmessages.vkobjects.VKGroup

class VKGroupList(val list: ArrayList<VKGroup>) : AbstractList<VKGroup>(), RandomAccess {

    override val size: Int
        get() = list.size

    override fun get(index: Int): VKGroup {
        return list[index]
    }

}