package com.example.vkmessages.comparators

import com.example.vkmessages.vkobjects.VKGroup

class VKGroupComparator : Comparator<VKGroup> {
    override fun compare(o1: VKGroup?, o2: VKGroup?): Int {

        if (o1 != null && o2 != null) {
            return o1.id.compareTo(o2.id)
        }
        return -1;
    }
}