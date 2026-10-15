package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CardBorder
import com.example.ui.theme.CardBorderGlow
import com.example.ui.theme.CardDark
import com.example.ui.theme.CardDarkSurface
import com.example.ui.theme.NeonCyan

@Composable
fun NeonFloatingCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 22.dp,
    hasGlowBorder: Boolean = false,
    testTag: String = "neon_card",
    content: @Composable ColumnScope.() -> Unit
) {
    val borderBrush = if (hasGlowBorder) {
        Brush.linearGradient(
            colors = listOf(
                NeonCyan.copy(alpha = 0.8f),
                CardBorderGlow.copy(alpha = 0.4f),
                CardBorder,
                Color(0xFF0F223D)
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                CardBorderGlow.copy(alpha = 0.45f),
                CardBorder,
                Color(0xFF111827)
            )
        )
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            CardDarkSurface,
            CardDark,
            Color(0xFF04060B)
        )
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (hasGlowBorder) 10.dp else 4.dp,
                shape = RoundedCornerShape(cornerRadius),
                spotColor = if (hasGlowBorder) NeonCyan else Color.Black,
                ambientColor = Color.Black
            )
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                width = if (hasGlowBorder) 1.5.dp else 1.dp,
                brush = borderBrush,
                shape = RoundedCornerShape(cornerRadius)
            )
            .testTag(testTag),
        color = Color.Transparent,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Column(
            modifier = Modifier
                .background(backgroundBrush)
                .padding(20.dp),
            content = content
        )
    }
}
