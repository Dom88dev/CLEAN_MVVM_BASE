package com.dom.data.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val cloudiness: Int
)
