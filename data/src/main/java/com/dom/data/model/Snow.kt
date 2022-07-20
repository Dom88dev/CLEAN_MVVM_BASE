package com.dom.data.model

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("1h") val volumeFor1h: Float,
    @SerializedName("3h") val volumeFor3h: Float
)
