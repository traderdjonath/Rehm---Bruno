package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.BioSiteData
import com.example.ui.theme.CardDark
import com.example.ui.theme.CardDarkSurface
import com.example.ui.theme.MetallicBlue
import com.example.ui.theme.MetallicSilver
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

enum class ActionButtonType {
    WHATSAPP,
    INSTAGRAM,
    NEON_DEFAULT
}

fun openExternalUrl(context: Context, url: String) {
    try {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        try {
            val fallback = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(fallback)
        } catch (e2: Exception) {
            Toast.makeText(context, "Acesse: $url", Toast.LENGTH_LONG).show()
        }
    }
}

fun openWhatsAppDirect(context: Context, url: String = BioSiteData.WHATSAPP_URL) {
    Toast.makeText(context, "Abrindo WhatsApp...", Toast.LENGTH_SHORT).show()
    try {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        openExternalUrl(context, url)
    }
}

fun openInstagramDirect(context: Context, url: String = BioSiteData.INSTAGRAM_URL) {
    Toast.makeText(context, "Abrindo Instagram...", Toast.LENGTH_SHORT).show()
    try {
        val uri = Uri.parse(url)
        val appIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.instagram.android")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(appIntent)
    } catch (e: Exception) {
        openExternalUrl(context, url)
    }
}

@Composable
fun ActionButton3D(
    title: String,
    subtitle: String,
    badgeText: String? = null,
    @DrawableRes iconDrawableRes: Int,
    targetUrl: String,
    modifier: Modifier = Modifier,
    buttonType: ActionButtonType = ActionButtonType.NEON_DEFAULT,
    pulseAnimation: Boolean = false,
    testTag: String = "action_button_3d"
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val infiniteTransition = rememberInfiniteTransition(label = "btn_pulse")
    val pulseScale by if (pulseAnimation) {
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.03f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse_scale"
        )
    } else {
        remember { androidx.compose.runtime.mutableFloatStateOf(1f) }
    }

    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "button_scale"
    )

    val combinedScale = pulseScale * pressScale

    // Colors and borders tailored to authentic brands
    val (borderBrush, backgroundBrush, spotColor, badgeBg, badgeTextColor) = when (buttonType) {
        ActionButtonType.WHATSAPP -> {
            Tuple5(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF25D366),
                        Color(0xFF128C7E),
                        Color(0xFF00E676),
                        Color(0xFF25D366)
                    )
                ),
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF072412),
                        Color(0xFF04170B),
                        Color(0xFF020E06)
                    )
                ),
                Color(0xFF25D366),
                Color(0xFF0E3D1E),
                Color(0xFF25D366)
            )
        }
        ActionButtonType.INSTAGRAM -> {
            Tuple5(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF833AB4),
                        Color(0xFFFD1D1D),
                        Color(0xFFFCAF45),
                        Color(0xFF833AB4)
                    )
                ),
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF200918),
                        Color(0xFF14050E),
                        Color(0xFF0B0307)
                    )
                ),
                Color(0xFFFD1D1D),
                Color(0xFF380E24),
                Color(0xFFFF7A00)
            )
        }
        ActionButtonType.NEON_DEFAULT -> {
            Tuple5(
                Brush.horizontalGradient(
                    listOf(
                        NeonCyan,
                        MetallicBlue,
                        Color(0xFF0056B3),
                        NeonCyan
                    )
                ),
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0D1E38),
                        Color(0xFF071120),
                        Color(0xFF040812)
                    )
                ),
                NeonCyan,
                Color(0xFF0E3866),
                NeonCyan
            )
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .scale(combinedScale)
            .shadow(
                elevation = if (buttonType == ActionButtonType.WHATSAPP || pulseAnimation) 14.dp else 8.dp,
                shape = RoundedCornerShape(22.dp),
                spotColor = spotColor,
                ambientColor = spotColor
            )
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = if (buttonType == ActionButtonType.WHATSAPP) 2.dp else 1.5.dp,
                brush = borderBrush,
                shape = RoundedCornerShape(22.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                when (buttonType) {
                    ActionButtonType.WHATSAPP -> openWhatsAppDirect(context, targetUrl)
                    ActionButtonType.INSTAGRAM -> openInstagramDirect(context, targetUrl)
                    else -> openExternalUrl(context, targetUrl)
                }
            }
            .testTag(testTag),
        color = Color.Transparent,
        shape = RoundedCornerShape(22.dp)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundBrush)
                .padding(horizontal = 18.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Real 3D Brand Icon
                Box(
                    modifier = Modifier
                        .size(66.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFF060910))
                        .border(
                            width = 1.2.dp,
                            brush = borderBrush,
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconDrawableRes),
                        contentDescription = title,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text Content
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (badgeText != null) {
                        Surface(
                            color = badgeBg,
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                0.8.dp,
                                badgeTextColor.copy(alpha = 0.8f)
                            ),
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                            ) {
                                if (buttonType == ActionButtonType.WHATSAPP) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF25D366))
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                                Text(
                                    text = badgeText.uppercase(),
                                    color = badgeTextColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp
                                )
                            }
                        }
                    }

                    Text(
                        text = title,
                        color = TextPrimary,
                        fontSize = 17.5.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.2.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = subtitle,
                        color = if (buttonType == ActionButtonType.WHATSAPP) Color(0xFFD1FAE5) else MetallicSilver,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Action arrow pill
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(badgeBg)
                        .border(
                            1.dp,
                            badgeTextColor.copy(alpha = 0.7f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Acessar",
                        tint = badgeTextColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

private data class Tuple5<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
)
