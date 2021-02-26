package com.example.vkmessages.vkobjects

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class VKMessage(
    val from: Int = 0,
    val lastText: String? = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(from)
        parcel.writeString(lastText)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKMessage> {
        override fun createFromParcel(parcel: Parcel): VKMessage {
            return VKMessage(parcel)
        }

        override fun newArray(size: Int): Array<VKMessage?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject) = VKMessage(
            from = json.optInt("from_id", 0),
            lastText = json.optString("text", "")
        )
    }
}