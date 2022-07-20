package com.dom.data.model

import com.google.gson.annotations.SerializedName

data class SysInfo(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)
