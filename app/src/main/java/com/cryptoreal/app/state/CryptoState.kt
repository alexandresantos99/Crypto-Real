package com.cryptoreal.app.state

import com.cryptoreal.app.models.Currency

sealed class CryptoState {
    data class Success(var data: List<Pair<String, Currency>>) : CryptoState()
    object Error : CryptoState()
    object Initial : CryptoState()
}
