package io.github.feliperce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.feliperce.avatar.AvatarVariant
import io.github.feliperce.avatar.BoringAvatar
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val palettes = listOf(
            listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90)),
            listOf(Color(0xFF264653), Color(0xFF2A9D8F), Color(0xFFE9C46A), Color(0xFFF4A261), Color(0xFFE76F51)),
            listOf(Color(0xFF6b705c), Color(0xFFa5a58d), Color(0xFFb7b7a4), Color(0xFFffe8d6), Color(0xFFddbea9)),
            listOf(Color(0xFFffcdb2), Color(0xFFffb4a2), Color(0xFFe5989b), Color(0xFFb58285), Color(0xFF6d597a)),
            listOf(Color(0xFF003049), Color(0xFFd62828), Color(0xFFf77f00), Color(0xFFfcbf49), Color(0xFFeae2b7))
        )

        var name by remember { mutableStateOf("Pimpolho") }
        var size by remember { mutableStateOf(120f) }
        var isSquare by remember { mutableStateOf(false) }
        var selectedPaletteIndex by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Boring Avatars Sandbox",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name / Seed") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        Button(onClick = {
                            val names = listOf("Pimpolho", "Kotlin", "Compose", "Multiplatform", "Avatar", "Boring", "Design")
                            name = names.random()
                        }) {
                            Text("Random")
                        }
                    }

                    Column {
                        Text("Size: ${size.toInt()}dp", style = MaterialTheme.typography.bodyMedium)
                        Slider(
                            value = size,
                            onValueChange = { size = it },
                            valueRange = 40f..300f
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Square?")
                        Switch(checked = isSquare, onCheckedChange = { isSquare = it })
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Color Palette", style = MaterialTheme.typography.bodyMedium)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            palettes.forEachIndexed { index, palette ->
                                FilterChip(
                                    selected = selectedPaletteIndex == index,
                                    onClick = { selectedPaletteIndex = index },
                                    label = {
                                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                            palette.forEach { color ->
                                                Box(
                                                    modifier = Modifier
                                                        .size(12.dp)
                                                        .clip(CircleShape)
                                                        .background(color)
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Text("Variants", style = MaterialTheme.typography.titleLarge)

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AvatarVariant.entries.forEach { variant ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BoringAvatar(
                            name = name,
                            colors = palettes[selectedPaletteIndex],
                            size = size.dp,
                            variant = variant,
                            shape = if (isSquare) RoundedCornerShape(0.dp) else CircleShape
                        )
                        Text(variant.name, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}