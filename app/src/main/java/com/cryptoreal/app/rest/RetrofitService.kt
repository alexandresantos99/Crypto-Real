package com.cryptoreal.app.rest

import com.cryptoreal.app.models.CurrencyRates
import retrofit2.http.GET

interface RetrofitService {

    @GET("exchange_rates")
    suspend fun getExchangeRates(): CurrencyRates

}