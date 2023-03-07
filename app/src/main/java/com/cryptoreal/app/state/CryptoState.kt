package com.cryptoreal.app.state

import com.cryptoreal.app.models.CurrencyRates

sealed class CryptoState {
    data class Success(var data: CurrencyRates?) : CryptoState()
    object Error : CryptoState()
    object Initial : CryptoState()
}
