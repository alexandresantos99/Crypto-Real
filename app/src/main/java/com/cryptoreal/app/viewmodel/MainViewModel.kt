package com.cryptoreal.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoreal.app.repository.MainRepository
import com.cryptoreal.app.state.CryptoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _currencyRates: MutableStateFlow<CryptoState> =
        MutableStateFlow(CryptoState.Initial)
    val currencyRates: StateFlow<CryptoState> = _currencyRates

    init {
        viewModelScope.launch {
            repository.getCurrencyRates().collect { currencyRates ->
                _currencyRates.value = when {
                    currencyRates.isNotEmpty() -> CryptoState.Success(currencyRates)
                    else -> CryptoState.Error
                }
            }
        }
    }

    fun refreshCurrencyRates() {
        viewModelScope.launch {
            while (true) {
                delay(60000)
                repository.getCurrencyRates().collect { currencyRates ->
                    _currencyRates.value = when {
                        currencyRates.isNotEmpty() -> {
                            CryptoState.Success(currencyRates)
                        }
                        else -> CryptoState.Error
                    }
                }
            }
        }
    }
}
