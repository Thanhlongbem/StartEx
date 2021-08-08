package com.example.stardemoapp.core.rest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RestService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(OkInterceptor.gsonConverter)
            .client(OkInterceptor.client)
            .build()
    }

    fun <T> createService(interfaceClazz: Class<T>): T {
        return retrofit.create(interfaceClazz)
    }
}