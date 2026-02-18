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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TicketConfirmationScreen(
    ticketNumber: String = "42",
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    var confirmed by remember { mutableStateOf(false) }

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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.6f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = ticketNumber,
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // QR code placeholder — swap for a real QR composable (e.g. ZXing)
                    Box(
                        modifier = Modifier
                            .size(220.dp)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "QR CODE\n#$ticketNumber",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD3D3D3)),

                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Thank you for choosing to attend our event. " +
                                "Your number has been generated. " +
                                "Please show this to event staff.",
                        fontSize = 16.sp,
                        color = Color.Black,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = onCancel,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA8A8A8)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(vertical = 14.dp)
                        ) {
                            Text(
                                text = "Cancel",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }

                        Button(
                            onClick = {
                                confirmed = true
                                onConfirm()
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF8B5CF6)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(vertical = 14.dp)
                        ) {
                            Text(
                                text = "Confirm",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}