package com.example.vkmessages.vkobjects

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class VKPost(
    var pictures: ArrayList<String>? = arrayListOf(),
    val groupId: Int = 0,
    val text: String? = "",
    var group : VKGroup? = null,
    val time : Long = 0
)