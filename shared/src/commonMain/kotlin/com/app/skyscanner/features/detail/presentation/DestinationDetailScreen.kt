package com.app.skyscanner.features.detail.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.app.skyscanner.core.constants.AppColors
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

    LaunchedEffect(destinationId) {
        viewModel.loadDestination(destinationId)
    }

    val destination = state.destination

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = AppColors.ink)
        }
    } else if (destination != null) {
        Scaffold(
            content = { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = padding.calculateBottomPadding())
                ) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                            Image(
                                painter = rememberAsyncImagePainter(destination.imageUrl),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
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
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                                .background(AppColors.surface)
                                .padding(24.dp)
                                .offset(y = (-24).dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = destination.city,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.ink
                                )
                                Badge(
                                    containerColor = AppColors.ink,
                                    contentColor = Color.White
                                ) {
                                    Text("★ ${destination.rating}", modifier = Modifier.padding(4.dp))
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(Color.Green)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${destination.country} • ${destination.reviewCount} Reviews",
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
                                fontSize = 16.sp,
                                lineHeight = 22.sp
                            )
                            Text(
                                text = if (isExpanded) "Read less" else "Read more",
                                color = AppColors.ink,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { isExpanded = !isExpanded }.padding(vertical = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Upcoming tours", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("See all", color = AppColors.inkSoft, fontSize = 14.sp)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Tours list
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
        )
    }
}

@Composable
fun CircleIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.8f),
        modifier = Modifier.size(40.dp).clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            tint = AppColors.ink
        )
    }
}
