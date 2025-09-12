package com.example.cryptocurrencycompose.di

import com.example.cryptocurrencycompose.core.data.network.HttpClientFactory
import com.example.cryptocurrencycompose.crypto.data.data_source.CoinDataSourceImpl
import com.example.cryptocurrencycompose.crypto.domain.data_source.CoinDataSource
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::CoinDataSourceImpl).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}