package com.cryptoreal.app.di.modules

import com.cryptoreal.app.rest.RetrofitService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { providesRetrofitApi() }
}

private fun providesRetrofitApi() = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(RetrofitService::class.java)
