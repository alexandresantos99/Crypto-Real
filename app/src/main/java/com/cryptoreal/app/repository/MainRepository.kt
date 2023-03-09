package com.cryptoreal.app.repository

import com.cryptoreal.app.models.Currency
import com.cryptoreal.app.rest.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(private val retrofit: RetrofitService) {
    fun getCurrencyRates(): Flow<List<Pair<String, Currency>>> = flow {
        val currencyRates = retrofit.getExchangeRates()
        emit(currencyRates)
    }.catch { catch ->
        emit(emptyList())
    }
}