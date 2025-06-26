package com.dom.data.remote

import com.dom.data.model.ApiResult
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<ApiResult.UserDto>
}