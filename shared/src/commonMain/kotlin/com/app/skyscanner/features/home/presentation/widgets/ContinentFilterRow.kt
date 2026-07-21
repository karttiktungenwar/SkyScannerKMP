package com.app.skyscanner.features.home.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.skyscanner.features.detail.presentation.widgets.TravelPillChip

@Composable
fun ContinentFilterRow(
    categories: List<String>,
    selectedContinent: String,
    onContinentSelect: (String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier) {
        items(categories.size) { index ->
            val category = categories[index]
            val isSelected = category == selectedContinent
            TravelPillChip(
                label = category,
                isSelected = isSelected,
                onClick = { onContinentSelect(category) }
            )
        }
    }
}
