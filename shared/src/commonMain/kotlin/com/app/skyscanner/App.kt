package com.app.skyscanner

import com.app.skyscanner.presentation.home.screens.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.skyscanner.core.theme.SkyScannerTheme
import kotlinx.serialization.Serializable
import org.koin.compose.KoinContext
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.compose.LocalPlatformContext
import org.koin.core.parameter.parametersOf

import com.app.skyscanner.features.detail.presentation.screens.DestinationDetailScreen
import org.koin.compose.koinInject

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val destinationId: String)

@Composable
@Preview
fun App() {
    KoinContext {
        val context = LocalPlatformContext.current
        val imageLoader = koinInject<ImageLoader> { parametersOf(context) }
        setSingletonImageLoaderFactory { imageLoader }

        SkyScannerTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = HomeRoute,
            ) {
                composable<HomeRoute> {
                    HomeScreen(navController = navController)
                }
                composable<DetailRoute> { backStackEntry ->
                    val route: DetailRoute = backStackEntry.toRoute()
                    DestinationDetailScreen(
                        destinationId = route.destinationId,
                        navController = navController
                    )
                }
            }
        }
    }
}