package com.ajuenterprises.xpayback.di

import com.ajuenterprises.xpayback.retrofit.Api
import com.ajuenterprises.xpayback.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit) : Api {
        return retrofit.create(Api::class.java)
    }

}