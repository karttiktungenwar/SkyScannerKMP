package com.app.skyscanner.core.common.wigets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

@Composable
fun HomeAppBar(
    title: String,
    subtitle: String,
    avatarUrl: String? = null,
    avatarRadius: Int = 20,
    action: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp
                    )
                )
            }
        },
        actions = {
            if (avatarUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(avatarUrl),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(avatarRadius.dp * 2)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            if (action != null) {
                action()
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    )
}