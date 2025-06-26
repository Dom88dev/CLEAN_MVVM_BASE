package com.dom.domain.usecase

import com.dom.domain.repository.Repository

class UseCaseUsers(private val repository: Repository) {
    suspend fun execute() = repository.getUserList()
}