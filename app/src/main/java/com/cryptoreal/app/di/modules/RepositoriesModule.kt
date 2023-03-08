package com.cryptoreal.app.di.modules

import com.cryptoreal.app.repository.MainRepository
import com.cryptoreal.app.rest.RetrofitService
import org.koin.dsl.module

val repositoriesModule = module {
    single { providesMainRepository(get()) }
}

private fun providesMainRepository(retrofit: RetrofitService) = MainRepository(retrofit = retrofit)
