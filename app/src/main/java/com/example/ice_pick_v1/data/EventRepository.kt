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
import java.time.LocalDateTime
import java.util.UUID

data class Event(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val location: String,
    val dateTime: LocalDateTime,
    val hostUserId: String,
    val maxAttendees: Int,
    val attendees: MutableList<String> = mutableListOf(),
    val tags: List<String> = emptyList()
)

object EventRepository {

    private val events = mutableListOf(
        Event(
            title = "Rock Climbing Night",
            description = "Indoor bouldering session. All skill levels welcome.",
            location = "Altitude Gym",
            dateTime = LocalDateTime.now().plusDays(2),
            hostUserId = "user_1",
            maxAttendees = 8,
            tags = listOf("Fitness", "Climbing", "Social")
        ),
        Event(
            title = "Pokemon GO Walk",
            description = "Casual evening walk and raid session.",
            location = "Lake Eola",
            dateTime = LocalDateTime.now().plusDays(4),
            hostUserId = "user_2",
            maxAttendees = 15,
            tags = listOf("Gaming", "Outdoor", "Pokemon")
        )
    )

    fun getAllEvents(): List<Event> = events

    fun getEventById(id: String): Event? =
        events.find { it.id == id }

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun joinEvent(eventId: String, userId: String): Boolean {
        val event = getEventById(eventId) ?: return false

        if (event.attendees.size >= event.maxAttendees) return false
        if (event.attendees.contains(userId)) return false

        event.attendees.add(userId)
        return true
    }

    fun leaveEvent(eventId: String, userId: String): Boolean {
        val event = getEventById(eventId) ?: return false
        return event.attendees.remove(userId)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EventRepositoryData() {

    val events = EventRepository.getAllEvents()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        events.forEach { event ->
            Text(text = "ID: ${event.id}")
            Text(text = "Title: ${event.title}")
            Text(text = "Description: ${event.description}")
            Text(text = "Location: ${event.location}")
            Text(text = "DateTime: ${event.dateTime}")
            Text(text = "Host: ${event.hostUserId}")
            Text(text = "Max: ${event.maxAttendees}")
            Text(text = "Attendees: ${event.attendees}")
            Text(text = "Tags: ${event.tags}")
            Text(text = "---------------------------")
        }
    }
}
