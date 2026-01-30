// ProfileScreen.kt
package com.example.ice_pick_v1.ui.screens.ProfileScreen// ProfileScreen.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ice_pick_v1.R
import com.example.ice_pick_v1.ui.theme.Icepickv1Theme
import com.example.ice_pick_v1.ui.theme.Purple80

@Composable
fun ProfileScreen(navController: NavController) {
    ProfileWindow(modifier = Modifier.fillMaxSize())
}

@Composable
fun ProfileWindow(modifier: Modifier) {
    val locateQuery = remember { mutableStateOf("") }
    val adminMode = remember { mutableStateOf(false) }

    val bg = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2A3340),
            Color(0xFF1F2731),
            Color(0xFF1B222B)
        )
    )

    Box(
        modifier = modifier
            .background(bg)
            .padding(horizontal = 18.dp, vertical = 14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            // Top bar (UI only)
            TopLocateBar(
                query = locateQuery.value,
                onQueryChange = { locateQuery.value = it }
            )

            Spacer(Modifier.height(16.dp))

            // Profile header block (UI only)
            ProfileHeader(
                name = "Eric Trinque",
                tags = "#Tags: Coder, CI/CD,\nTech, Startup,\nFounder, Mobile,\nArchery, Disc-Golf,\nMaker, Carpentry"
            )

            Spacer(Modifier.height(18.dp))

            // Friends
            SectionBlock(
                title = "Friends",
                items = listOf("J.James", "P.Jones", "M.Jules")
            )

            Spacer(Modifier.height(14.dp))

            // History
            SectionBlock(
                title = "History",
                items = listOf("CodeCrazy", "Sailaway", "MakerZ")
            )

            Spacer(Modifier.weight(1f))

            // Bottom buttons (UI only)
            BottomActions()
        }
    }
}

// UI Pieces (no icon refs)

@Composable
private fun TopLocateBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "Radar" placeholder
        Text(
            text = "◉",
            color = Color(0xFFBFC9D6),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 2.dp)
        )

        Spacer(Modifier.width(12.dp))

        val fieldColors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF3B4757),
            unfocusedContainerColor = Color(0xFF3B4757),
            disabledContainerColor = Color(0xFF3B4757),
            focusedTextColor = Color(0xFFE6EDF6),
            unfocusedTextColor = Color(0xFFE6EDF6),
            focusedPlaceholderColor = Color(0xFFBFC9D6),
            unfocusedPlaceholderColor = Color(0xFFBFC9D6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Locate") },
            singleLine = true,
            shape = RoundedCornerShape(22.dp),
            colors = fieldColors,
            modifier = Modifier
                .weight(1f)
                .height(44.dp),
            // "Mic" placeholder
            trailingIcon = {
                Text(
                    text = "⌁",
                    color = Color(0xFFBFC9D6),
                    modifier = Modifier.padding(end = 12.dp)
                )
            }
        )

        Spacer(Modifier.width(10.dp))

        // "Hamburger" placeholder
        Text(
            text = "≡",
            color = Color(0xFFBFC9D6),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(end = 2.dp)
        )
    }
}

@Composable
private fun ProfileHeader(
    name: String,
    tags: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Big profile image (use existing logo as placeholder)
        Image(
            painter = painterResource(id = R.drawable.icepick_logo),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(118.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(14.dp))

        // Info card on right
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD8DEE7)),
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
                        color = Color(0xFF1D232B),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = tags,
                        color = Color(0xFF1D232B),
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // "Edit" placeholder (UI only)
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFEEF2F7),
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(22.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "✎",
                            color = Color(0xFF1D232B),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionBlock(
    title: String,
    items: List<String>
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8DEE7)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    ) {
        Column(Modifier.fillMaxSize().padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1D232B)
                )
                Spacer(Modifier.width(10.dp))
                // Small section icon placeholder
                Text(text = "•", color = Color(0xFF1D232B))
            }

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.take(3).forEach { label ->
                    MiniBubbleCard(label = label)
                }
            }
        }
    }
}

@Composable
private fun MiniBubbleCard(label: String) {
    Box(
        modifier = Modifier
            .width(104.dp)
            .height(140.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F6FA)),
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
                    color = Color(0xFF1D232B),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Bubble avatar placeholder
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
                painter = painterResource(id = R.drawable.icepick_logo),
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
private fun BottomActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* UI only */ },
            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
        ) { Text("Account") }

        Spacer(Modifier.width(12.dp))

        Button(
            onClick = { /* UI only */ },
            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
        ) { Text("Settings") }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    Icepickv1Theme {
        ProfileWindow(modifier = Modifier.fillMaxSize())
    }
}
