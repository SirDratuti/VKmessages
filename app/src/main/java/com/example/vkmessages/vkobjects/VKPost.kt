package com.example.vkmessages.vkobjects

import android.os.Parcel
import android.os.Parcelable

class VKPost(
    val id: Int = 0,
    val pictureUrl: String? = "",
    val avatarUrl: String? = "",
    val publicName: String? = "",
    val text: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(pictureUrl)
        parcel.writeString(avatarUrl)
        parcel.writeString(publicName)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKPost> {
        override fun createFromParcel(parcel: Parcel): VKPost {
            return VKPost(parcel)
        }

        override fun newArray(size: Int): Array<VKPost?> {
            return arrayOfNulls(size)
        }

    }


}