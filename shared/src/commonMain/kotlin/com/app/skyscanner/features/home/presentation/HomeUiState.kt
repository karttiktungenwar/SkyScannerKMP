package com.app.skyscanner.presentation.home

import com.app.skyscanner.core.common.domain.enitity.Destination

data class HomeUiState(
    val categories: List<String> = emptyList(),
    val selectedContinent: String = "Asia",
    val destinations: List<Destination> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTab: Int = 0
)