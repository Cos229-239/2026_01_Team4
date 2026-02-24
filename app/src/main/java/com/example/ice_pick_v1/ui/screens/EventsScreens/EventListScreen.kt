package com.example.ice_pick_v1.ui.screens.EventsScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Event(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EventsListScreen(
    events: List<Event> = sampleEvents(),
    onEventClick: (Event) -> Unit = {},
    onFavoriteToggle: (Event) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Locate") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.MyLocation,
                                contentDescription = "Location"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Voice search"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5A7A94)
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF8FA9BC)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events) { event ->
                EventCard(
                    event = event,
                    onClick = { onEventClick(event) },
                    onFavoriteToggle = { onFavoriteToggle(event) }
                )
            }

            // Page indicators at bottom
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8B5CF6))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                }
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(event.isFavorite) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Event Image
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for image - replace with actual image loading
                Text(
                    text = "Event\nImage",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Event Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = event.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = event.description,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    lineHeight = 16.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Favorite Button
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    onFavoriteToggle()
                }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) Color(0xFFFFD700) else Color.Gray
                )
            }
        }
    }
}

// Sample data for preview/testing
private fun sampleEvents() = listOf(
    Event(
        id = "1",
        name = "Event Name",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tempus condimentum massa sed bibendum. Integer id neque feugiat, ullamcorper est vitae, molestie dolor. Maecenas sit amet quam sed ipsum finibus porttitor. Sed nulla elit, ultricies eu feugiat et, auctor quis.",
        imageUrl = ""
    ),
    Event(
        id = "2",
        name = "Event Name",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tempus condimentum massa sed bibendum. Integer id neque feugiat, ullamcorper est vitae, molestie dolor. Maecenas sit amet quam sed ipsum finibus porttitor. Sed nulla elit.",
        imageUrl = ""
    ),
    Event(
        id = "3",
        name = "Event Name",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tempus condimentum massa sed bibendum. Integer id neque feugiat, ullamcorper est vitae, molestie dolor. Maecenas sit amet quam sed ipsum finibus porttitor. Sed nulla elit, ultricies eu feugiat et, auctor quis.",
        imageUrl = ""
    ),
    Event(
        id = "4",
        name = "Event Name",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a ipsum mauris. Morbi porta mauris at nunc pharetra, ut condimentum nibh feugiat. Aliquam in molestie.",
        imageUrl = ""
    ),
    Event(
        id = "5",
        name = "Event Name",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tempus condimentum massa sed bibendum. Integer id neque feugiat, ullamcorper est vitae, molestie dolor. Maecenas sit amet quam sed ipsum finibus porttitor. Sed nulla elit, ultricies eu feugiat et, auctor quis.",
        imageUrl = ""
    )
)