package com.cryptoreal.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cryptoreal.app.commons.CryptoConstants.AUD
import com.cryptoreal.app.commons.CryptoConstants.BTC
import com.cryptoreal.app.commons.CryptoConstants.ETH
import com.cryptoreal.app.commons.CryptoConstants.USD
import com.cryptoreal.app.databinding.ActivityMainBinding
import com.cryptoreal.app.models.CurrencyRates
import com.cryptoreal.app.repository.MainRepository
import com.cryptoreal.app.rest.RetrofitService
import com.cryptoreal.app.state.CryptoState
import com.cryptoreal.app.view.adapter.MainAdapter
import com.cryptoreal.app.viewmodel.MainViewModel
import com.cryptoreal.app.viewmodel.MainViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val retrofitService by lazy {
        RetrofitService.getInstance()
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(MainRepository(retrofitService))
        )[MainViewModel::class.java]
    }

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter
        subscribeApi()
        bindDate()
    }

    private fun subscribeApi() {
        lifecycleScope.launchWhenCreated {
            viewModel.currencyRates.collect {
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
            it == USD || it == BTC || it == AUD || it == ETH }?.toList()
        adapter.submitList(filterList)
        viewModel.refreshCurrencyRates()
        bindDate()
    }

    private fun bindDate() {
        val outputFormat = DateTimeFormatter.ofPattern("'Dia' dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss", Locale("pt", "BR"))
        binding.tvDateHour.text = LocalDateTime.now().format(outputFormat)
    }
}