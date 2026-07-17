package com.app.skyscanner.app.di

import com.app.skyscanner.core.common.data.repository.TravelRepositoryImpl
import com.app.skyscanner.core.common.domain.repository.TravelRepository
import com.app.skyscanner.presentation.home.HomeViewModel
import com.app.skyscanner.features.detail.presentation.DetailViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.context.startKoin

val appModule = module {
    singleOf(::TravelRepositoryImpl) bind TravelRepository::class
    factoryOf(::HomeViewModel)
    factoryOf(::DetailViewModel)
}

private var isKoinStarted = false

fun initKoin() {
    if (!isKoinStarted) {
        try {
            startKoin {
                modules(appModule)
            }
        } catch (e: Exception) {
            // Already started or other error
        }
        isKoinStarted = true
    }
}
