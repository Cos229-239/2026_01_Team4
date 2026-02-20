package com.example.ice_pick_v1.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
data class User(
    val id: String,
    val name: String,
    val username: String,
    val bio: String,
    val interests: List<String>,
    val rating: Double = 5.0,
    val hostedEvents: MutableList<String> = mutableListOf()
)

object UserRepository {

    private val users = mutableListOf(
        User(
            id = "user_1",
            name = "Brandon",
            username = "@bk_dev",
            bio = "Software engineer student. Into climbing, gaming, and building cool things.",
            interests = listOf("Climbing", "Gaming", "Faith", "Tech")
        ),
        User(
            id = "user_2",
            name = "Eric",
            username = "@etrinque",
            bio = "Design-focused dev. Love event planning and community.",
            interests = listOf("UI/UX", "Pokemon", "Social Events")
        )
    )

    fun getAllUsers(): List<User> = users

    fun getUserById(id: String): User? =
        users.find { it.id == id }

    fun addUser(user: User) {
        users.add(user)
    }

    fun addHostedEvent(userId: String, eventId: String) {
        val user = getUserById(userId) ?: return
        user.hostedEvents.add(eventId)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_UserRepositoryData() {

    val users = UserRepository.getAllUsers()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        users.forEach { user ->
            Text(text = "ID: ${user.id}")
            Text(text = "Name: ${user.name}")
            Text(text = "Username: ${user.username}")
            Text(text = "Bio: ${user.bio}")
            Text(text = "Interests: ${user.interests}")
            Text(text = "Rating: ${user.rating}")
            Text(text = "Hosted Events: ${user.hostedEvents}")
            Text(text = "---------------------------")
        }
    }
}
