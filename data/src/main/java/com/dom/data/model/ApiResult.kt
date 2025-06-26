package com.dom.data.model

import com.google.gson.annotations.SerializedName

sealed class ApiResult {
    data class UserDto(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("username") val userName: String,
        @SerializedName("email") val email: String,
        @SerializedName("address") val address: UserAddress,
        @SerializedName("phone") val phone: String,
        @SerializedName("website") val webSite: String,
        @SerializedName("company") val company: Company,
    ) : ApiResult() {
        data class Company(
            @SerializedName("name") val name: String,
            @SerializedName("catchPhrase") val catchPhrase: String,
            @SerializedName("bs") val bs: String,
        )
    }

    data class UserAddress(
        @SerializedName("street") val st: String,
        @SerializedName("suite") val suite: String,
        @SerializedName("city") val city: String,
        @SerializedName("zipcode") val zip: String,
        @SerializedName("geo") val geoCode: GeoCode,
    ) : ApiResult() {
        data class GeoCode(
            @SerializedName("lat") val lat: String,
            @SerializedName("lon") val lon: String,
        )
    }
}
