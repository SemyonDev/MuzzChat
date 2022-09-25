package com.muzz.muzzchat.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatMessage(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "type")
    val type: Int = 0,
    @field:Json(name = "timestamp")
    val timestamp: Long? = null,
    @field:Json(name = "status")
    val isViewed: Boolean = false,
    var isShowTimeStamp: Boolean = false,
) : Parcelable
