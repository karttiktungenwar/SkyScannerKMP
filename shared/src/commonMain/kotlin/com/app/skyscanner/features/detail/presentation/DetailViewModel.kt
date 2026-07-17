package com.app.skyscanner.features.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.skyscanner.core.common.domain.enitity.Destination
import com.app.skyscanner.core.common.domain.enitity.Tour
import com.app.skyscanner.core.common.domain.repository.TravelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailUiState(
    val destination: Destination? = null,
    val tours: List<Tour> = emptyList(),
    val isLoading: Boolean = false
)

class DetailViewModel(
    private val repository: TravelRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadDestination(id: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val destination = repository.getDestinationById(id)
            val tours = repository.getToursForDestination(id)
            _uiState.update { it.copy(destination = destination, tours = tours, isLoading = false) }
        }
    }
}
