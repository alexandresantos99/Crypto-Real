package com.cryptoreal.app.di.modules

import com.cryptoreal.app.repository.MainRepository
import com.cryptoreal.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { providesMainViewModel(get()) }
}

fun providesMainViewModel(mainRepository: MainRepository): MainViewModel {
    return MainViewModel(
        repository = mainRepository
    )
}