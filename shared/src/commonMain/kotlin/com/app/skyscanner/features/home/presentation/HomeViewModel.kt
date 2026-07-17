package com.app.skyscanner.presentation.home

import com.app.skyscanner.core.common.domain.repository.TravelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TravelRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            val cats = repository.getCategories()
            _uiState.update { it.copy(categories = cats) }
            selectContinent("Asia")
        }
    }

    fun selectContinent(continent: String) {
        _uiState.update { it.copy(selectedContinent = continent, isLoading = true) }
        viewModelScope.launch {
            val list = repository.getDestinationsByContinent(continent)
            _uiState.update { it.copy(destinations = list, isLoading = false) }
        }
    }

    fun selectTab(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }
}