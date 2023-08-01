package com.example.inseminator.di

import com.example.inseminator.ui.login.LoginViewModel
import io.reactivex.schedulers.Schedulers.single
import org.koin.dsl.module

val viewmodelmodules = module { single { LoginViewModel(get()) } }