package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.BioSiteData
import com.example.model.FaqItem
import com.example.model.ServiceItem
import com.example.model.TestimonialItem
import com.example.ui.components.ActionButton3D
import com.example.ui.components.ActionButtonType
import com.example.ui.components.NeonFloatingCard
import com.example.ui.components.PulsingWhatsAppFab
import com.example.ui.components.QuoteCalculatorCard
import com.example.ui.components.openExternalUrl
import com.example.ui.components.openInstagramDirect
import com.example.ui.components.openWhatsAppDirect
import com.example.ui.theme.BadgeBg
import com.example.ui.theme.CardBorder
import com.example.ui.theme.CardBorderGlow
import com.example.ui.theme.CardDark
import com.example.ui.theme.CardDarkSurface
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.GoldStar
import com.example.ui.theme.MetallicBlue
import com.example.ui.theme.MetallicSilver
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun BioSiteScreen(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val showStickyBar by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = DarkBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                AnimatedVisibility(
                    visible = showStickyBar,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    StickyBottomBar(
                        onWhatsAppClick = { openWhatsAppDirect(context, BioSiteData.WHATSAPP_URL) },
                        onInstagramClick = { openInstagramDirect(context, BioSiteData.INSTAGRAM_URL) }
                    )
                }
            }
        ) { innerPadding ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag("bio_site_scroll_container"),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 120.dp
                ),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                // 1. HEADER / LOGO SECTION
                item {
                    HeaderSection()
                }

                // 2. PRESENTATION / SLOGAN SECTION
                item {
                    PresentationSloganSection()
                }

                // 3. MAIN ACTION BUTTONS (WHATSAPP & INSTAGRAM 3D)
                item {
                    MainActionButtonsSection()
                }

                // 4. CRAFTSMANSHIP & SHOWCASE CARD
                item {
                    CraftsmanshipShowcaseCard()
                }

                // 5. SERVICES CARDS
                item {
                    ServicesSection()
                }

                // 6. WHY CHOOSE REHM (+10 YEARS)
                item {
                    WhyChooseRehmSection()
                }

                // 7. INSTANT QUOTE SIMULATOR
                item {
                    QuoteCalculatorCard(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // 8. TESTIMONIALS
                item {
                    TestimonialsSection()
                }

                // 9. FAQ ACCORDION
                item {
                    FaqSection()
                }

                // 10. FINAL CONTACT & CTAS
                item {
                    FinalContactSection()
                }
            }
        }

        // Botão WhatsApp pulsante na tela para ir direto para o contato
        PulsingWhatsAppFab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = if (showStickyBar) 68.dp else 0.dp),
            onClick = {
                openWhatsAppDirect(context, BioSiteData.WHATSAPP_URL)
            }
        )
    }
}

@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = Color(0xFF091629),
            shape = RoundedCornerShape(20.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, NeonCyan.copy(alpha = 0.6f)),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(NeonCyan)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ATENDIMENTO DISPONÍVEL",
                    color = NeonCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(26.dp),
                    spotColor = NeonCyan,
                    ambientColor = MetallicBlue
                )
                .clip(RoundedCornerShape(26.dp))
                .border(
                    width = 1.8.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            NeonCyan.copy(alpha = 0.9f),
                            MetallicBlue,
                            Color(0xFF0A1C36),
                            NeonCyan.copy(alpha = 0.5f)
                        )
                    ),
                    shape = RoundedCornerShape(26.dp)
                )
                .background(Color(0xFF030509)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_rehm_logo),
                contentDescription = "Logomarca REHM Instalações e Rodapés",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun PresentationSloganSection() {
    NeonFloatingCard(
        hasGlowBorder = true,
        testTag = "presentation_slogan_card"
    ) {
        Text(
            text = "ESPECIALISTA EM",
            color = NeonCyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Instalação de Portas\ne Rodapés",
            color = TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = NeonCyan,
                    ambientColor = MetallicBlue
                )
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF0D2547),
                            Color(0xFF071426),
                            Color(0xFF040A14)
                        )
                    )
                )
                .border(
                    width = 1.8.dp,
                    brush = Brush.horizontalGradient(
                        listOf(
                            NeonCyan,
                            MetallicBlue,
                            Color(0xFF00E5FF),
                            MetallicSilver
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(vertical = 14.dp, horizontal = 18.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "+10 ANOS DE EXPERIÊNCIA",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = GoldStar,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Referência em Alto Padrão",
                        color = MetallicSilver,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = NeonCyan,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Qualidade e compromisso!",
                color = NeonCyan,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.3.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Acabamentos milimétricos, cortes precisos a 45° sem frestas e ambiente limpo durante toda a execução.",
            color = TextSecondary,
            fontSize = 13.5.sp,
            lineHeight = 19.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MainActionButtonsSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        ActionButton3D(
            title = "Solicitar Orçamento no WhatsApp",
            subtitle = "Resposta rápida • Falar com Bruno Santos",
            badgeText = "Online Agora",
            iconDrawableRes = R.drawable.img_whatsapp_real,
            targetUrl = BioSiteData.WHATSAPP_URL,
            buttonType = ActionButtonType.WHATSAPP,
            pulseAnimation = true,
            testTag = "whatsapp_main_button"
        )

        ActionButton3D(
            title = "Acompanhar no Instagram",
            subtitle = "@bruno_santospaz • Vídeos de Obras Recentes",
            badgeText = "Perfil Oficial",
            iconDrawableRes = R.drawable.img_instagram_real,
            targetUrl = BioSiteData.INSTAGRAM_URL,
            buttonType = ActionButtonType.INSTAGRAM,
            pulseAnimation = false,
            testTag = "instagram_main_button"
        )
    }
}

@Composable
private fun CraftsmanshipShowcaseCard() {
    NeonFloatingCard(
        hasGlowBorder = false,
        testTag = "craftsmanship_showcase_card"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "PADRÃO REHM",
                    color = NeonCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Galeria de Acabamento",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Surface(
                color = Color(0xFF0F264A),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(0.8.dp, NeonCyan)
            ) {
                Text(
                    text = "ALTO LUXO",
                    color = NeonCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_work_showcase),
                contentDescription = "Instalação de portas de madeira nobre e rodapés de poliestireno impecáveis",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Surface(
                color = Color(0xCC050810),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Corte 45° Santa Luzia & Portas em Madeira",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CraftsmanshipBadge("Meia-Esquadria 45°", Modifier.weight(1f))
            CraftsmanshipBadge("Alinhamento Laser", Modifier.weight(1f))
            CraftsmanshipBadge("Vedação Limpa", Modifier.weight(1f))
        }
    }
}

@Composable
private fun CraftsmanshipBadge(label: String, modifier: Modifier = Modifier) {
    Surface(
        color = Color(0xFF09111F),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E2E46)),
        modifier = modifier
    ) {
        Text(
            text = label,
            color = MetallicSilver,
            fontSize = 10.5.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
            maxLines = 1
        )
    }
}

@Composable
private fun ServicesSection() {
    NeonFloatingCard(
        hasGlowBorder = false,
        testTag = "services_section_card"
    ) {
        Text(
            text = "SOLUÇÕES COMPLETAS",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Especialidades de Instalação",
            color = TextPrimary,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            BioSiteData.services.forEach { service ->
                ServiceItemRow(service)
            }
        }
    }
}

@Composable
private fun ServiceItemRow(service: ServiceItem) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF0A101C))
            .border(
                1.dp,
                if (expanded) NeonCyan.copy(alpha = 0.7f) else CardBorder,
                RoundedCornerShape(14.dp)
            )
            .clickable { expanded = !expanded }
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    color = Color(0xFF0D2547),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = service.badge.uppercase(),
                        color = NeonCyan,
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = service.title,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = NeonCyan,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = service.description,
            color = TextSecondary,
            fontSize = 12.5.sp,
            lineHeight = 17.sp
        )

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                HorizontalDivider(color = Color(0xFF1B273A), thickness = 0.8.dp)
                Spacer(modifier = Modifier.height(8.dp))
                service.features.forEach { feature ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = feature,
                            color = MetallicSilver,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WhyChooseRehmSection() {
    NeonFloatingCard(
        hasGlowBorder = true,
        testTag = "why_choose_section"
    ) {
        Text(
            text = "DIFERENCIAIS EXCLUSIVOS",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Por que escolher a REHM?",
            color = TextPrimary,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PillarRow(
                icon = Icons.Default.Build,
                title = "Alinhamento & Nível a Laser",
                subtitle = "Portas que abrem e fecham suavemente sem raspar ou bater."
            )
            PillarRow(
                icon = Icons.Default.CleaningServices,
                title = "Obra 100% Limpa",
                subtitle = "Aspiração ativa de pó durante o corte e proteção completa do seu piso."
            )
            PillarRow(
                icon = Icons.Default.Schedule,
                title = "Pontualidade Rigorosa",
                subtitle = "Início e entrega no dia combinado sem atrasar o seu cronograma."
            )
            PillarRow(
                icon = Icons.Default.Shield,
                title = "Garantia Total de Execução",
                subtitle = "Tranquilidade com mais de 10 anos de experiência comprovada no mercado."
            )
        }
    }
}

@Composable
private fun PillarRow(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF090E19))
            .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF0E223D))
                .border(1.dp, NeonCyan.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = NeonCyan,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                color = TextSecondary,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun TestimonialsSection() {
    NeonFloatingCard(
        hasGlowBorder = false,
        testTag = "testimonials_section"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "OPINIÃO DE QUEM CONFIA",
                    color = NeonCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Depoimentos de Clientes",
                    color = TextPrimary,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = MetallicBlue,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            BioSiteData.testimonials.forEach { item ->
                TestimonialCard(item)
            }
        }
    }
}

@Composable
private fun TestimonialCard(item: TestimonialItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF080D17))
            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = item.name,
                    color = TextPrimary,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${item.role} • ${item.location}",
                    color = NeonCyan,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Row {
                repeat(item.rating) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldStar,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "\"${item.comment}\"",
            color = MetallicSilver,
            fontSize = 12.5.sp,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun FaqSection() {
    NeonFloatingCard(
        hasGlowBorder = false,
        testTag = "faq_section"
    ) {
        Text(
            text = "DÚVIDAS FREQUENTES",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Perguntas e Respostas",
            color = TextPrimary,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            BioSiteData.faqs.forEach { faq ->
                FaqAccordionItem(faq)
            }
        }
    }
}

@Composable
private fun FaqAccordionItem(faq: FaqItem) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF090E19))
            .border(1.dp, if (expanded) NeonCyan.copy(alpha = 0.5f) else CardBorder, RoundedCornerShape(12.dp))
            .clickable { expanded = !expanded }
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = faq.question,
                color = TextPrimary,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = NeonCyan,
                modifier = Modifier.size(20.dp)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = faq.answer,
                color = TextSecondary,
                fontSize = 12.5.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun FinalContactSection() {
    NeonFloatingCard(
        hasGlowBorder = true,
        testTag = "final_contact_section"
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = Color(0xFF0A1F3B),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, NeonCyan.copy(alpha = 0.7f))
            ) {
                Text(
                    text = "ATENDIMENTO DIRETO",
                    color = NeonCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Vamos Transformar sua Obra?",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Entre em contato agora para verificar a disponibilidade de agenda e receber um orçamento sem compromisso.",
                color = TextSecondary,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(18.dp))

            ActionButton3D(
                title = "Chamar no WhatsApp Agora",
                subtitle = "https://wa.link/0d3d10",
                badgeText = "Online Agora",
                iconDrawableRes = R.drawable.img_whatsapp_real,
                targetUrl = BioSiteData.WHATSAPP_URL,
                buttonType = ActionButtonType.WHATSAPP,
                pulseAnimation = true,
                testTag = "final_whatsapp_cta"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ActionButton3D(
                title = "Visitar Perfil no Instagram",
                subtitle = BioSiteData.INSTAGRAM_HANDLE,
                badgeText = "Siga-nos",
                iconDrawableRes = R.drawable.img_instagram_real,
                targetUrl = BioSiteData.INSTAGRAM_URL,
                buttonType = ActionButtonType.INSTAGRAM,
                testTag = "final_instagram_cta"
            )

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider(color = Color(0xFF1B273A), thickness = 0.8.dp)

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "REHM Instalações e Rodapés",
                color = MetallicSilver,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Responsável: Bruno Santos Paz",
                color = TextSecondary,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Especialista em Portas & Rodapés • +10 Anos de Experiência",
                color = TextTertiary,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun StickyBottomBar(
    onWhatsAppClick: () -> Unit,
    onInstagramClick: () -> Unit
) {
    Surface(
        color = Color(0xEE05070D),
        shadowElevation = 16.dp,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(Color.Transparent, NeonCyan.copy(alpha = 0.5f), Color.Transparent)),
                shape = RectangleShape
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF150A14))
                    .border(
                        1.2.dp,
                        Brush.linearGradient(
                            listOf(Color(0xFF833AB4), Color(0xFFFD1D1D), Color(0xFFFCAF45))
                        ),
                        RoundedCornerShape(14.dp)
                    )
                    .clickable { onInstagramClick() }
                    .testTag("sticky_instagram_button"),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_instagram_real),
                    contentDescription = "Instagram",
                    modifier = Modifier.size(38.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Surface(
                onClick = onWhatsAppClick,
                shape = RoundedCornerShape(16.dp),
                color = Color.Transparent,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = Color(0xFF25D366))
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.8.dp,
                        Brush.horizontalGradient(
                            listOf(Color(0xFF25D366), Color(0xFF00E676), Color(0xFF25D366))
                        ),
                        RoundedCornerShape(16.dp)
                    )
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF08381A), Color(0xFF04200E))
                        )
                    )
                    .testTag("sticky_whatsapp_button")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_whatsapp_real),
                        contentDescription = "WhatsApp",
                        modifier = Modifier.size(34.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "Orçamento no WhatsApp",
                            color = Color.White,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Resposta Imediata",
                            color = Color(0xFF34D399),
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
