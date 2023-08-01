package com.example.inseminator

import android.app.Application
import com.example.inseminator.di.coremodules
import com.example.inseminator.di.repositorymodules
import com.example.inseminator.di.viewmodelmodules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewmodelmodules,
                    coremodules,
                    repositorymodules,
                )
            )
        }
    }
}