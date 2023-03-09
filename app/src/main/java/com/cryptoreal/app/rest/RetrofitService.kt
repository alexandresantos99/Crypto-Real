package com.cryptoreal.app.rest

import com.cryptoreal.app.models.Currency
import retrofit2.http.GET

interface RetrofitService {

    @GET("cryptos")
    suspend fun getExchangeRates(): List<Pair<String, Currency>>

}