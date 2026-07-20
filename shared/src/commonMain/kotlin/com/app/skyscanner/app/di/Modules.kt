package com.app.skyscanner.app.di

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.app.skyscanner.core.common.data.repository.TravelRepositoryImpl
import com.app.skyscanner.core.common.domain.repository.TravelRepository
import com.app.skyscanner.presentation.home.HomeViewModel
import com.app.skyscanner.features.detail.presentation.DetailViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.context.startKoin

val appModule = module {
    // 1. Explicitly instantiate HttpClient with an engine
    single {
        HttpClient() {
        }
    }

    // 2. Pass the explicitly-configured HttpClient to Coil
    factory { (context: PlatformContext) ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory(get<HttpClient>()))
            }
            .build()
    }
    // Inject the ImageLoader configured in Koin
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
