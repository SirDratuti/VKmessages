package com.example.vkmessages.containers

class VKPostsList<VKPost>(val list: ArrayList<VKPost>) : AbstractList<VKPost>(), RandomAccess {

    override val size: Int
        get() = list.size

    override fun get(index: Int): VKPost {
        return list[index]
    }
}