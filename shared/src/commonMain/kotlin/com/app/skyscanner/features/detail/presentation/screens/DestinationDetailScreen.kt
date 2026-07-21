package com.app.skyscanner.features.detail.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.app.skyscanner.core.common.wigets.CircleIconButton
import com.app.skyscanner.features.detail.presentation.widgets.TravelInfoBadge
import com.app.skyscanner.features.detail.presentation.widgets.TravelSectionHeader
import com.app.skyscanner.core.constants.AppColors
import com.app.skyscanner.features.detail.presentation.DetailViewModel
import com.app.skyscanner.features.detail.presentation.widgets.TourCard
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DestinationDetailScreen(
    destinationId: String,
    navController: NavController
) {
    val viewModel: DetailViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(destinationId) {
        viewModel.loadDestination(destinationId)
    }

    val destination = state.destination

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = AppColors.coral)
        }
    } else if (destination != null) {
        Scaffold(
            containerColor = AppColors.background) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.background)
                    .padding(bottom = padding.calculateBottomPadding())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // 1. Hero Image Header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalPlatformContext.current)
                                .data(destination.imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.2f))
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            CircleIconButton(
                                icon = Icons.AutoMirrored.Filled.ArrowBack,
                                onClick = { navController.popBackStack() }
                            )
                            CircleIconButton(
                                icon = Icons.Default.FavoriteBorder,
                                onClick = { /* Handle favorite */ }
                            )
                        }
                    }

                    // 2. Content Sheet with Rounded Top Corners & Overlap
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-28).dp) // Pulls the sheet up to overlap the image header
                            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                            .background(AppColors.surface)
                            .padding(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = destination.city,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.ink
                                )
                                Text(
                                    text = destination.country,
                                    color = AppColors.inkSoft,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                            Badge(containerColor = AppColors.coral, contentColor = Color.White) {
                                Text("★ ${destination.rating}", modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(AppColors.mint)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${destination.reviewCount} reviews",
                                color = AppColors.inkSoft,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        var isExpanded by remember { mutableStateOf(false) }
                        Text(
                            text = destination.description,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                            color = AppColors.inkSoft,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = if (isExpanded) "Read less" else "Read more",
                            color = AppColors.ink,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable { isExpanded = !isExpanded }.padding(vertical = 6.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                        TravelSectionHeader(title = "Upcoming tours", subtitle = "See all")
                        Spacer(modifier = Modifier.height(12.dp))

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(state.tours.size) { index ->
                                TourCard(
                                    tour = state.tours[index],
                                    onClick = { /* Navigate to tour detail */ }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

