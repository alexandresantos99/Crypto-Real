package com.cryptoreal.app.di.modules

import com.cryptoreal.app.rest.RetrofitService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { providesRetrofitApi() }
}

private fun providesRetrofitApi() = Retrofit.Builder()
    .baseUrl("https://api.coingecko.com/api/v3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(RetrofitService::class.java)
