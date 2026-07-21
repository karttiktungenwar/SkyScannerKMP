package com.app.skyscanner.features.home.presentation.widgets
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Warning

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.app.skyscanner.core.common.domain.enitity.Destination
import com.app.skyscanner.core.common.wigets.RatingBadge

@Composable
fun DestinationCard(
    destination: Destination,
    isFavorite: Boolean,
    onFavoriteTap: () -> Unit,
    onSeeMoreTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .height(340.dp)
            .clip(RoundedCornerShape(28.dp))
    ) {
        // 1. Background Image
        SubcomposeAsyncImage(
            model = destination.imageUrl,
            contentDescription = "${destination.city}, ${destination.country}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            error = {
                   Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = "Error loading image",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )

        // 2. Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        0.4f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.55f)
                    )
                )
        )

        // 3. Top-Right Favorite Button
        IconButton(
            onClick = onFavoriteTap,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .size(40.dp)
                .background(Color.Transparent, CircleShape)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (isFavorite) Color(0xFFFF5252) else Color.White
            )
        }

        // 4. Content Details (Country, City, Rating)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, end = 20.dp, bottom = 76.dp)
        ) {
            Text(
                text = destination.country,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = destination.city,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            RatingBadge(
                rating = destination.rating,
                modifier = Modifier.align(Alignment.Start),
                reviewCount = destination.reviewCount,
                textColor = Color.White, // AppColors.star equivalent
                starColor = Color(0xFFFFC107),
            )
        }

        // 5. "See More" Action Bar at bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
                .height(44.dp)
                .border(width = 1.dp, color = Color.White.copy(alpha = 0.6f), shape = CircleShape)
                .clip(CircleShape)
                .clickable(onClick = onSeeMoreTap)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 2.dp), // Adjust padding for button alignment
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Spacer matching icon width to balance alignment
                Spacer(modifier = Modifier.width(40.dp))

                Text(
                    text = "See more",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W600,
                    color = Color.White,
                    fontSize = 14.sp
                )

                // Arrow Circle Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = "See More",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}