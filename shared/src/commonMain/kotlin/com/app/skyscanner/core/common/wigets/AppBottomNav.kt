package com.app.skyscanner.core.common.wigets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.app.skyscanner.core.constants.AppColors

@Composable
fun AppBottomNav(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Menu,
        Icons.Default.FavoriteBorder,
        Icons.Default.GridView
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(AppColors.ink)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icons.forEachIndexed { index, icon ->
                val isSelected = index == selectedIndex
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) Color(0xFFFFD700) else Color.White, // Yellow if selected
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onItemSelected(index) }
                )
            }
        }
    }
}
