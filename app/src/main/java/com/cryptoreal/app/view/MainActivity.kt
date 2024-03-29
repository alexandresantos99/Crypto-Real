package com.cryptoreal.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cryptoreal.app.commons.CryptoConstants.AUD
import com.cryptoreal.app.commons.CryptoConstants.BTC
import com.cryptoreal.app.commons.CryptoConstants.ETH
import com.cryptoreal.app.commons.CryptoConstants.USD
import com.cryptoreal.app.databinding.ActivityMainBinding
import com.cryptoreal.app.models.CurrencyRates
import com.cryptoreal.app.state.CryptoState
import com.cryptoreal.app.view.adapter.MainAdapter
import com.cryptoreal.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModel<MainViewModel>()

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter
        subscribeApi()
        bindDate()
    }

    private fun subscribeApi() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.currencyRates.collect {
                when (it) {
                    is CryptoState.Success -> bindUi(it.data)
                    is CryptoState.Error -> {}
                    is CryptoState.Initial -> {}
                }
            }
        }
    }

    private fun bindUi(data: CurrencyRates?) {
        val filterList = data?.rates?.filterKeys {
            it == USD || it == BTC || it == AUD || it == ETH
        }?.toList()
        adapter.submitList(filterList)
        mainViewModel.refreshCurrencyRates()
        bindDate()
    }

    private fun bindDate() {
        val outputFormat = DateTimeFormatter.ofPattern(
            "'Dia' dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss",
            Locale("pt", "BR")
        )
        binding.tvDateHour.text = LocalDateTime.now().format(outputFormat)
    }
}