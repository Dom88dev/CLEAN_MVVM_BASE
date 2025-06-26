package com.dom.domain.repository

import com.dom.domain.model.Data

interface Repository {
    suspend fun getUserList() : List<Data.User>
}