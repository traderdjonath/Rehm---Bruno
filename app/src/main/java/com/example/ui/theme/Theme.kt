package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MetallicBlue,
    onPrimary = Color.White,
    primaryContainer = CardDarkSurface,
    onPrimaryContainer = NeonCyan,
    secondary = NeonCyan,
    onSecondary = DeepBlack,
    secondaryContainer = BadgeBg,
    onSecondaryContainer = MetallicSilver,
    tertiary = MetallicSilver,
    onTertiary = DeepBlack,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = CardDark,
    onSurface = TextPrimary,
    surfaceVariant = CardDarkSurface,
    onSurfaceVariant = TextSecondary,
    outline = CardBorder,
    outlineVariant = CardBorderGlow
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
