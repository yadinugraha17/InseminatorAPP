package com.example.inseminator.di

import com.example.inseminator.core.repository.CoreRepository
import io.reactivex.schedulers.Schedulers.single
import org.koin.dsl.module

val repositorymodules = module { single { CoreRepository(get ()) } }