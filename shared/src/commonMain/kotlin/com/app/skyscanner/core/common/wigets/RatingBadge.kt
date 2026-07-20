package com.app.skyscanner.core.common.wigets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingBadge(
    rating: Double,
    modifier: Modifier = Modifier,
    reviewCount: Int? = null,
    textColor: Color = Color.Unspecified, // Replace with your AppColors.ink equivalent
    starColor: Color? = null,
    format: String.Companion.(String, Double) -> Unit
) {
    val activeStarColor = starColor ?: textColor

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Container Box with Border
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = activeStarColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 10.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.StarBorder,
                contentDescription = "Star Rating",
                modifier = Modifier.size(16.dp),
                tint = activeStarColor
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = String.format("%.1f", rating).toString(),
                fontSize = 13.sp,
                fontWeight = FontWeight.W600,
                color = textColor
            )
        }

        // Optional Review Count Text
        if (reviewCount != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$reviewCount reviews",
                fontSize = 12.sp,
                color = textColor.copy(alpha = 0.7f),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}