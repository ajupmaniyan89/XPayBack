package com.ajuenterprises.xpayback.repository

import com.ajuenterprises.xpayback.model.User
import com.ajuenterprises.xpayback.model.UserResponse
import com.ajuenterprises.xpayback.retrofit.Api
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {

    suspend fun getUsers(page: Int, size: Int): UserResponse {
        return api.getItems(page, size)
    }

   suspend fun getUserDetail(userId:Int): User {
        return api.getUser(userId)
    }
}