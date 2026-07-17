package com.app.skyscanner.core.common.wigets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.skyscanner.core.constants.AppColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchTextField(
    hintText: String = "Search",
    prefixIcon: ImageVector? = Icons.Default.Search,
    suffixIcon: ImageVector? = null,
    onChanged: (String) -> Unit = {},
    onSuffixPressed: () -> Unit = {},
    controller: TextFieldValue? = null,
) {
    // Use a mutable state for the text field value
    var text by remember { mutableStateOf(TextFieldValue("")) }

    // Update the state if a controller is provided
    LaunchedEffect(controller) {
        if (controller != null) {
            text = controller
        }
    }

    // Text style
    val textStyle = TextStyle(
        fontSize = 16.sp,
        color = AppColors.ink,
    )

    // Hint text style
    val hintTextStyle = textStyle.copy(color = AppColors.inkSoft)

    BasicTextField(
        value = text,
        onValueChange = { newValue ->
            text = newValue
            onChanged(newValue.text)
        },
        textStyle = textStyle,
        visualTransformation = VisualTransformation.None,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(AppColors.surface)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        cursorBrush = SolidColor(AppColors.ink),
    ) { innerTextField ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Prefix icon
            if (prefixIcon != null) {
                Icon(
                    imageVector = prefixIcon,
                    contentDescription = "Prefix Icon",
                    tint = AppColors.inkSoft,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            // Text field
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.text.isEmpty()) {
                    Text(
                        text = hintText,
                        style = hintTextStyle,
                    )
                }
                innerTextField()
            }

            // Suffix icon
            if (suffixIcon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = onSuffixPressed,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = suffixIcon,
                        contentDescription = "Suffix Icon",
                        tint = AppColors.inkSoft,
                    )
                }
            }
        }
    }
}