package com.example.ice_pick_v1.ui.screens.RadarScreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun RadarScreenLooksOnly(
    modifier: Modifier = Modifier
) {
    var rangeMiles by remember { mutableStateOf(10f) }
    var showEvents by remember { mutableStateOf(true) }
    var showPeople by remember { mutableStateOf(true) }

    // Static “fake” blips (no APIs). Regenerates once per composition.
    val blips = remember {
        buildList {
            repeat(10) { i ->
                add(
                    RadarBlip(
                        id = "evt_$i",
                        type = RadarBlipType.EVENT,
                        label = listOf("Pickup Frisbee", "Climbing", "Coffee", "Study Group", "Game Night")[i % 5],
                        distanceMiles = Random.nextFloat() * 25f,
                        angleDeg = Random.nextFloat() * 360f
                    )
                )
            }
            repeat(8) { i ->
                add(
                    RadarBlip(
                        id = "per_$i",
                        type = RadarBlipType.PERSON,
                        label = listOf("Eric", "Maddie", "Josh", "Sam", "Alex")[i % 5],
                        distanceMiles = Random.nextFloat() * 25f,
                        angleDeg = Random.nextFloat() * 360f
                    )
                )
            }
        }
    }

    val filtered = remember(blips, showEvents, showPeople, rangeMiles) {
        blips
            .filter {
                (showEvents && it.type == RadarBlipType.EVENT) ||
                        (showPeople && it.type == RadarBlipType.PERSON)
            }
            .filter { it.distanceMiles <= rangeMiles }
            .sortedBy { it.distanceMiles }
            .take(12)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(18.dp, 14.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Radar",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            AssistChip(
                onClick = { /* looks-only */ },
                label = { Text("Demo") }
            )
        }

        Spacer(Modifier.height(10.dp))

        // Range slider (looks-only)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Range: ${rangeMiles.roundToInt()} mi",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.width(120.dp)
            )
            Slider(
                value = rangeMiles,
                onValueChange = { rangeMiles = it },
                valueRange = 1f..25f,
                steps = 20,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            FilterChip(
                selected = showEvents,
                onClick = { showEvents = !showEvents },
                label = { Text("Events") }
            )
            FilterChip(
                selected = showPeople,
                onClick = { showPeople = !showPeople },
                label = { Text("People") }
            )
        }

        Spacer(Modifier.height(14.dp))

        // Radar area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            RadarLooksOnlyCanvas(
                blips = filtered,
                rangeMiles = rangeMiles
            )
        }

        Spacer(Modifier.height(12.dp))

        // Nearby list
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.padding(14.dp)) {
                Text(
                    "Nearby",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))

                if (filtered.isEmpty()) {
                    Text(
                        "No blips in range. Increase range to see activity.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    filtered.take(5).forEachIndexed { idx, b ->
                        NearbyRow(idx = idx, blip = b)
                        if (idx != minOf(4, filtered.size - 1)) {
                            Spacer(Modifier.height(10.dp))
                            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RadarLooksOnlyCanvas(
    blips: List<RadarBlip>,
    rangeMiles: Float
) {
    val infinite = rememberInfiniteTransition(label = "radar_sweep")
    val sweepAngle by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = LinearEasing)
        ),
        label = "sweep"
    )

    // Capture theme colors here (COMPOSABLE context)
    val cs = MaterialTheme.colorScheme
    val ring = cs.onBackground.copy(alpha = 0.16f)
    val grid = cs.onBackground.copy(alpha = 0.10f)
    val sweep = cs.primary.copy(alpha = 0.18f)
    val rim = cs.primary.copy(alpha = 0.28f)

    val eventColor = cs.tertiary
    val personColor = cs.primary

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clip(CircleShape)
            .background(cs.surfaceVariant.copy(alpha = 0.35f))
    ) {
//        Canvas(Modifier.fillMaxSize()) {
//            blips.forEach { b ->
//                val base = when (b.type) {
//                    RadarBlipType.EVENT -> eventColor
//                    RadarBlipType.PERSON -> personColor
//                }
//
//                // Glow + core
//                drawCircle(base.copy(alpha = 0.22f), 14f, /* pos */)
//                drawCircle(base.copy(alpha = 0.85f), 6f,  /* pos */)
//            }
//        }
        Canvas(Modifier.fillMaxSize()) {
            val maxRadius = min(size.width, size.height) / 2f
            val usableRadius = maxRadius * 0.90f

            blips.forEach { b ->
                val base = when (b.type) {
                    RadarBlipType.EVENT -> eventColor
                    RadarBlipType.PERSON -> personColor
                }

                // distance normalized to current radar range
                val t = (b.distanceMiles / rangeMiles).coerceIn(0f, 1f)

                // degrees -> radians
                val rad = Math.toRadians(b.angleDeg.toDouble())

                // polar -> cartesian (centered)
                val r = t * usableRadius
                val x = (kotlin.math.cos(rad) * r).toFloat()
                val y = (kotlin.math.sin(rad) * r).toFloat()
                val pos = center + Offset(x, y)

                // Glow + core
                drawCircle(color = base.copy(alpha = 0.22f), radius = 14f, center = pos)
                drawCircle(color = base.copy(alpha = 0.85f), radius = 6f, center = pos)
            }
        }
    }
}


@Composable
private fun NearbyRow(idx: Int, blip: RadarBlip) {
    val typeLabel = when (blip.type) {
        RadarBlipType.EVENT -> "Event"
        RadarBlipType.PERSON -> "Person"
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = (idx + 1).toString(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(blip.label, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(2.dp))
            Text(
                "$typeLabel • ${blip.distanceMiles.roundToInt()} mi",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        TextButton(onClick = { /* looks-only */ }) { Text("Open") }
    }
}

/* ----------------------------- Model ----------------------------- */

enum class RadarBlipType { EVENT, PERSON }

data class RadarBlip(
    val id: String,
    val type: RadarBlipType,
    val label: String,
    val distanceMiles: Float,
    val angleDeg: Float
)

/* ----------------------------- Preview ----------------------------- */

@Preview(showBackground = true)
@Composable
private fun Preview_RadarScreenLooksOnly() {
    MaterialTheme {
        RadarScreenLooksOnly()
    }
}
