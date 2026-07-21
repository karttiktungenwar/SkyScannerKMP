package com.app.skyscanner.features.detail.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.skyscanner.core.constants.AppColors

@Composable
fun TravelPillChip(
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    selectedContainerColor: Color = AppColors.coral,
    unselectedContainerColor: Color = AppColors.cardSurface,
    selectedTextColor: Color = Color.White,
    unselectedTextColor: Color = AppColors.inkSoft,
) {
    val containerColor = if (isSelected) selectedContainerColor else unselectedContainerColor
    val textColor = if (isSelected) selectedTextColor else unselectedTextColor

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(containerColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            color = textColor,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}
