package com.example.vkmessages.vkobjects

import android.os.Parcel
import android.os.Parcelable

class VKGroup(
    var id: Int = 0,
    val groupName: String? = "",
    val groupAvatarUrl: String? = ""
) : Parcelable, Comparable<VKGroup> {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(groupName)
        parcel.writeString(groupAvatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKGroup> {
        override fun createFromParcel(parcel: Parcel): VKGroup {
            return VKGroup(parcel)
        }

        override fun newArray(size: Int): Array<VKGroup?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: VKGroup): Int {
        return id.compareTo(other.id)
    }
}