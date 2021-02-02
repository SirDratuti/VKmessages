package com.example.vkmessages.vkobjects

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class VKConversation(
    val id: Int = 0,
    val last: VKMessage?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(VKMessage::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeParcelable(last, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKConversation> {
        override fun createFromParcel(parcel: Parcel): VKConversation {
            return VKConversation(parcel)
        }

        override fun newArray(size: Int): Array<VKConversation?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject) : Int = json.optInt("id", 0)
    }

}