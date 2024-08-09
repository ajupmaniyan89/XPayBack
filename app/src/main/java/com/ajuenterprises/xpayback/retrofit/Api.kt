package com.ajuenterprises.xpayback.retrofit


import com.ajuenterprises.xpayback.model.User
import com.ajuenterprises.xpayback.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("users")
    suspend fun getItems(@Query("limit") limit: Int, @Query("skip") skip: Int): UserResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}