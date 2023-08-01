package com.example.inseminator.di

import com.example.inseminator.core.data.api.RemoteDataSource
import com.example.inseminator.core.data.api.network.NetworkClient
import io.reactivex.schedulers.Schedulers.single
import org.koin.dsl.module

val coremodules = module {
    single { NetworkClient.getApiService() }
    single { RemoteDataSource(get ()) }
}