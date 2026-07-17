package com.app.skyscanner.presentation.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.navigation.NavController
import com.app.skyscanner.core.constants.AppColors
import com.app.skyscanner.presentation.home.HomeViewModel
import com.app.skyscanner.presentation.home.widgets.ContinentFilterRow
import com.app.skyscanner.presentation.home.widgets.DestinationCard
import com.app.skyscanner.core.common.wigets.HomeAppBar
import com.app.skyscanner.core.common.wigets.SearchTextField
import com.app.skyscanner.core.common.wigets.AppBottomNav
import com.app.skyscanner.DetailRoute

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            HomeAppBar(
                title = "Hello, Kartik",
                subtitle = "Welcome to TripGlide!",
                avatarUrl ="https://karttiktungenwar.github.io/portfolio/assets/img/my-profile-img.png",
            )
        },
        bottomBar = {
            AppBottomNav(
                selectedIndex = state.selectedTab,
                onItemSelected = { viewModel.selectTab(it) }
            )
        },
        content = { padding ->
            // Main content of the screen
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                SearchTextField(
                    suffixIcon= Icons.Default.Filter,
                )
                Spacer(modifier = Modifier.height(24.dp))
                // Continent Filter Chips
                ContinentFilterRow(
                    categories = state.categories,
                    selectedContinent = state.selectedContinent,
                    onContinentSelect = { viewModel.selectContinent(it) }
                )
                Spacer(modifier = Modifier.height(24.dp))
                if (state.destinations.isNotEmpty()) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(state.destinations.size) { index ->
                            val destination = state.destinations[index]
                            DestinationCard(
                                destination = destination,
                                onClick = { 
                                    navController.navigate(DetailRoute(destinationId = destination.id))
                                }
                            )
                        }
                    }
                }

                if (state.isLoading) {
                    Spacer(modifier = Modifier.height(32.dp))
                    CircularProgressIndicator(color = AppColors.ink)
                }
            }
        }
    )
}