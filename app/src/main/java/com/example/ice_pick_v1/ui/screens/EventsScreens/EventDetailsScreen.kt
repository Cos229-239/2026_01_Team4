package com.example.ice_pick_v1.ui.screens.EventsScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class EventImage(
    val url: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EventDetailScreen(
    eventName: String = "Event Name",
    eventDescription: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dignissim justo sit amet suscipit fermentum. Nunc ut est viverra, venenatis risus sed, interdum felis. Sed interdum consectetur mollis. Sed sed leo id dui fermentum dapibus. Sed lectus tellus, ultrices vel ornare id, malesuada at orci. Fusce ullamcorper mi eget neque.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dignissim justo sit amet suscipit fermentum. Nunc ut est viverra, venenatis risus sed, interdum felis. Sed interdum consectetur mollis. Sed sed leo id dui fermentum dapibus. Sed lectus tellus, ultrices vel ornare id, malesuada at orci. Fusce ullamcorper mi eget neque.",
    images: List<EventImage> = listOf(
        EventImage("", "Sailing event"),
        EventImage("", "Event photo 2"),
        EventImage("", "Event photo 3"),
        EventImage("", "Event photo 4")
    ),
    onRegister: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF8FA9BC))
                .verticalScroll(rememberScrollState())
        ) {
            // Image Carousel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                ) { page ->
                    // Image placeholder - replace with actual image loading
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Image ${page + 1}",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Page indicators
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(images.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (pagerState.currentPage == index)
                                        Color(0xFF8B5CF6)
                                    else
                                        Color.LightGray
                                )
                        )
                    }
                }
            }

            // Event Details Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = eventName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = eventDescription,
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            Button(
                onClick = onRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6)
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    text = "Register",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}