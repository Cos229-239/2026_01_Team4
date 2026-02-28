package com.example.ice_pick_v1.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ice_pick_v1.ui.login.LoginScreen
import com.example.ice_pick_v1.ui.dashboard.DashboardScreen
import com.example.ice_pick_v1.ui.theme.Icepickv1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        enableEdgeToEdge()
        setContent {
            Log.d("MainActivity", "setContent called")
            Icepickv1Theme {
                Log.d("MainActivity", "Inside theme")
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Log.d("MainActivity", "Inside Surface")
                    NavigationStack()
                }
            }
        }
    }
}

// TODO: Move to Navigation/Screen.kt
// Routes for Navigation lookup
sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Dashboard : Screen("dashboard_screen")
}

// TODO: Move to ui/<splash or launch>/SplashScreen.kt
@Composable
fun SplashScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Button(onClick = {}, modifier = Modifier) { }
    }
}

// TODO: Move to Navigation/NavigationStack.kt
@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(modifier = Modifier, navController)
        }
    }
}