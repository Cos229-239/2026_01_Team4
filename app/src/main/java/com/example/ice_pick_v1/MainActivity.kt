package com.example.ice_pick_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ice_pick_v1.ui.theme.Icepickv1Theme
import com.example.ice_pick_v1.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Icepickv1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginWindow(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}


@Composable
fun LoginWindow(
    modifier: Modifier?
) {
    val userName = remember { mutableStateOf("") }
    val userPassword = remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        )
        {
            IcePickLogo()
            // Input Fields
            OutlinedTextField(
                value = userName.value, onValueChange = { userName.value = it },
                leadingIcon = {
                },
                label = {
                    Text(text = "username")
                },
                shape = CircleShape,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            )
            OutlinedTextField(
                value = userPassword.value, onValueChange = { userPassword.value = it },
                leadingIcon = {
                },
                label = {
                    Text(text = "password")
                },
                shape = CircleShape,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            // Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(50.dp)
                        .padding(start = 20.dp, top = 6.dp, end = 20.dp, bottom = 5.dp)
                ) { Text("Guest") }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.outlinedButtonColors(Purple80),
                    modifier = Modifier
                        .height(50.dp)
                        .padding(start = 20.dp, top = 6.dp, end = 20.dp, bottom = 5.dp)
                ) { Text("Submit") }
            }

            // SSO Login
            OutlinedCard(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("Continue with:", textAlign = TextAlign.Center)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        // TODO: Change to Icons
                        Text("Google")
                        Text("FaceBook")
                        Text("Github")
                        Text("LinkedIN")

                    }
                }
            }
            // Guest
            OutlinedButton(
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors(Purple80),
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 20.dp, top = 6.dp, end = 20.dp, bottom = 6.dp)
            ) { Text("Sign-Up") }
        }
    }
}

//@Composable
//fun BackgrounCard() {
//    Image(
//        painter = painterResource(id = R.drawable.admin_background_vector),
//        contentDescription = "Background SVG",
//        contentScale = ContentScale.FillBounds, // or another ContentScale
//        modifier = Modifier // ensures the image fills the Box
//    )
//}

@Composable
fun IcePickLogo() {
    Image(
        painter = painterResource(id = R.drawable.icepick_logo), // Use the generated drawable name
        contentDescription = "IcePick brand logo",
        modifier = Modifier.size(128.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Icepickv1Theme {
        LoginWindow(Modifier)
    }
}