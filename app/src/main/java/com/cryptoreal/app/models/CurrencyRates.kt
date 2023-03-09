package com.cryptoreal.app.models

import com.google.gson.annotations.SerializedName


data class CurrencyRates(
    @SerializedName("rates")
    val rates: Map<String, Currency>
)

data class Currency(
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("urlImage")
    val urlImage: String
)