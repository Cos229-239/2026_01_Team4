package com.example.ice_pick_v1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ice_pick_v1.R
import com.example.ice_pick_v1.ui.theme.Purple80

/* =========================
   Theme tokens (shared)
========================= */

object IcePickTokens {
    val BgTop = Color(0xFF2A3340)
    val BgMid = Color(0xFF1F2731)
    val BgBot = Color(0xFF1B222B)

    val TopBarField = Color(0xFF3B4757)
    val TopBarText = Color(0xFFE6EDF6)
    val TopBarHint = Color(0xFFBFC9D6)

    val Panel = Color(0xFFD8DEE7)
    val Card = Color(0xFFF4F6FA)
    val Ink = Color(0xFF1D232B)
    val Badge = Color(0xFFEEF2F7)

    val RadiusLg = 14.dp
    val RadiusMd = 12.dp
    val RadiusPill = 22.dp
}

// Layout helpers

@Composable
fun IcePickScreenBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val bg = Brush.verticalGradient(
        colors = listOf(IcePickTokens.BgTop, IcePickTokens.BgMid, IcePickTokens.BgBot)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(bg),
        content = content
    )
}

// Reusable UI parts

@Composable
fun IcePickTopSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    leftGlyph: String = "◉",
    rightGlyph: String = "≡",
    trailingGlyph: String = "⌁",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = leftGlyph,
            color = IcePickTokens.TopBarHint,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 2.dp)
        )

        Spacer(Modifier.width(12.dp))

        val fieldColors = TextFieldDefaults.colors(
            focusedContainerColor = IcePickTokens.TopBarField,
            unfocusedContainerColor = IcePickTokens.TopBarField,
            disabledContainerColor = IcePickTokens.TopBarField,
            focusedTextColor = IcePickTokens.TopBarText,
            unfocusedTextColor = IcePickTokens.TopBarText,
            focusedPlaceholderColor = IcePickTokens.TopBarHint,
            unfocusedPlaceholderColor = IcePickTokens.TopBarHint,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Locate") },
            singleLine = true,
            shape = RoundedCornerShape(IcePickTokens.RadiusPill),
            colors = fieldColors,
            modifier = Modifier
                .weight(1f)
                .height(44.dp),
            trailingIcon = {
                Text(
                    text = trailingGlyph,
                    color = IcePickTokens.TopBarHint,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = rightGlyph,
            color = IcePickTokens.TopBarHint,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(end = 2.dp)
        )
    }
}

@Composable
fun IcePickProfileHeader(
    name: String,
    tags: String,
    imageResId: Int = R.drawable.icepick_logo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(118.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(14.dp))

        Card(
            shape = RoundedCornerShape(IcePickTokens.RadiusLg),
            colors = CardDefaults.cardColors(containerColor = IcePickTokens.Panel),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .weight(1f)
                .height(118.dp)
        ) {
            Box(Modifier.fillMaxSize().padding(12.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 26.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Name: $name",
                        fontWeight = FontWeight.SemiBold,
                        color = IcePickTokens.Ink,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = tags,
                        color = IcePickTokens.Ink,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // edit badge placeholder
                Surface(
                    shape = CircleShape,
                    color = IcePickTokens.Badge,
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(22.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "✎", color = IcePickTokens.Ink, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Composable
fun IcePickSectionCard(
    title: String,
    modifier: Modifier = Modifier,
    headerGlyph: String = "•",
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(IcePickTokens.RadiusLg),
        colors = CardDefaults.cardColors(containerColor = IcePickTokens.Panel),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
    ) {
        Column(Modifier.fillMaxSize().padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title, fontWeight = FontWeight.SemiBold, color = IcePickTokens.Ink)
                Spacer(Modifier.width(10.dp))
                Text(text = headerGlyph, color = IcePickTokens.Ink)
            }

            Spacer(Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
fun IcePickMiniBubbleCard(
    label: String,
    imageResId: Int = R.drawable.icepick_logo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(104.dp)
            .height(140.dp)
    ) {
        Card(
            shape = RoundedCornerShape(IcePickTokens.RadiusMd),
            colors = CardDefaults.cardColors(containerColor = IcePickTokens.Card),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp, start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = label,
                    color = IcePickTokens.Ink,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Surface(
            shape = CircleShape,
            shadowElevation = 6.dp,
            color = Color.White,
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.TopCenter)
                .offset(y = 8.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Mini Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun IcePickPillButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {} // still UI-safe; caller can wire later
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Purple80),
        shape = RoundedCornerShape(IcePickTokens.RadiusPill),
        modifier = modifier.height(44.dp)
    ) { Text(text) }
}