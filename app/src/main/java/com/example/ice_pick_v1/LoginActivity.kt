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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ice_pick_v1.ui.theme.Icepickv1Theme
import com.example.ice_pick_v1.ui.theme.Purple80

//TODO: Swap to LoginActivity and add new MainActivity for splash screen
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
    val textFieldBackgroundColor = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        disabledContainerColor = Color.White,
    )

    var AdminLoginToggleState = remember { mutableStateOf(false) }

//    @Composable
//    fun adminloginToggled(toggled: MutableState<Boolean>) {
//        if (toggled.value) {
//            UserBackgrdounCard()
//            AdminBackgroundCard()
//        } else {
//            AdminBackgroundCard()
//            UserBackgroundCard()
//        }
//    }

    @Composable
    fun AdminLoginToggled(isAdmin: Boolean) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Gray user background
            UserBackgroundCard(
                modifier = Modifier.alpha(if (isAdmin) 0f else 1f)
            )
            // Green admin background
            AdminBackgroundCard(
                modifier = Modifier.alpha(if (isAdmin) 1f else 0f)
            )
        }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AdminLoginToggled(AdminLoginToggleState.value)
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        ) {
            Spacer(Modifier.height(64.dp))
            IcePickLogo(Modifier.padding())
            // Input Fields
            OutlinedTextField(
                value = userName.value, onValueChange = { userName.value = it },
                leadingIcon = {
                },
                label = {
                    Text(text = "username")
                },
                shape = CircleShape,
                colors = textFieldBackgroundColor,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp),
            )
            OutlinedTextField(
                value = userPassword.value, onValueChange = { userPassword.value = it },
                leadingIcon = {
                },
                label = {
                    Text(text = "password")
                },
                shape = CircleShape,
                colors = textFieldBackgroundColor,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            // Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 16.dp)
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
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
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
                        Text("Some Outside Source Link")
                    }
                }
            }

            // Sign-up
            OutlinedButton(
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors(Purple80),
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 20.dp, top = 6.dp, end = 20.dp, bottom = 6.dp)
            ) { Text("Sign-Up") }

            Switch(
                checked = AdminLoginToggleState.value,
                onCheckedChange = { AdminLoginToggleState.value = !AdminLoginToggleState.value },
            )
        }
    }

}

@Composable
fun AdminBackgroundCard(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.admin_background_vector),
        contentDescription = "Background SVG",
        modifier = modifier // ensures the image fills the Box
    )
}

@Composable
fun UserBackgroundCard(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.user_background_vector),
        contentDescription = "Background SVG",
        modifier = modifier // ensures the image fills the Box
    )
}


@Composable
fun IcePickLogo(modifier: Modifier) {
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