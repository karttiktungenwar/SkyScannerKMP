package com.app.skyscanner.presentation.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.navigation.NavController
import com.app.skyscanner.DetailRoute
import com.app.skyscanner.core.common.wigets.AppBottomNav
import com.app.skyscanner.core.common.wigets.HomeAppBar
import com.app.skyscanner.core.common.wigets.SearchTextField
import com.app.skyscanner.core.common.wigets.TravelSectionHeader
import com.app.skyscanner.core.constants.AppColors
import com.app.skyscanner.presentation.home.HomeViewModel
import com.app.skyscanner.features.home.presentation.widgets.ContinentFilterRow
import com.app.skyscanner.features.home.presentation.widgets.DestinationCard
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = AppColors.background,
        topBar = {
            HomeAppBar(
                title = "Hello, Kartik",
                subtitle = "Ready for your next escape?",
                avatarUrl = "https://karttiktungenwar.github.io/portfolio/assets/img/my-profile-img.png",
            )
        },
        bottomBar = {
            AppBottomNav(
                selectedIndex = state.selectedTab,
                onItemSelected = { viewModel.selectTab(it) }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(AppColors.background)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                SearchTextField(
                    hintText = "Search destinations",
                    suffixIcon = Icons.Default.Filter,
                )
                Spacer(modifier = Modifier.height(20.dp))
                TravelSectionHeader(title = "Popular regions", subtitle = "Explore")
                Spacer(modifier = Modifier.height(12.dp))
                ContinentFilterRow(
                    categories = state.categories,
                    selectedContinent = state.selectedContinent,
                    onContinentSelect = { viewModel.selectContinent(it) }
                )
                Spacer(modifier = Modifier.height(20.dp))
                TravelSectionHeader(title = "Featured trips", subtitle = "View all")
                Spacer(modifier = Modifier.height(12.dp))
                if (state.destinations.isNotEmpty()) {
                    val pagerState = rememberPagerState(
                        pageCount = { state.destinations.size }
                    )
                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 16.dp), // Screen edge padding
                        pageSpacing = 16.dp, // Space between cards
                        modifier = Modifier.fillMaxWidth()
                    ) { pageIndex ->
                        val destination = state.destinations[pageIndex]

                        DestinationCard(
                            destination = destination,
                            isFavorite = false,
                            onFavoriteTap = {  },
                            onSeeMoreTap = { navController.navigate(DetailRoute(destination.id)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                if (state.isLoading) {
                    Spacer(modifier = Modifier.height(32.dp))
                    CircularProgressIndicator(color = AppColors.coral)
                }
            }
        }
    )
}