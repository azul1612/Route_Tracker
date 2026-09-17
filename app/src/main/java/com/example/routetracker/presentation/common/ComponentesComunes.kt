package com.example.routetracker.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
val coloresLinea = mapOf(
    "linea1" to Color(0xFFC1121F), "linea2" to Color(0xFF6A1B9A),
    "linea3" to Color(0xFF9ACD32), "linea4" to Color(0xFF00BCD4),
    "linea5" to Color(0xFF1A237E), "linea6" to Color(0xFFE91E63),
    "linea7" to Color(0xFF1B5E20)
)

@Composable
fun MenuSuperior(onSalir: () -> Unit, onInicio: () -> Unit, onAtras: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = onSalir) {
            Icon(Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(4.dp))
            Text("Salir")
        }
        TextButton(onClick = onInicio) {
            Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color(0xFFC1121F))
            Spacer(Modifier.width(4.dp))
            Text("Inicio", color = Color(0xFFC1121F), fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = onAtras) {
            Text("Atrás")
            Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
fun SelectorLineas(lineaActivaId: String, onCambiarLinea: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        items((1..7).toList()) { numero ->
            val idLinea = "linea$numero"
            val color = coloresLinea[idLinea] ?: Color.Gray
            val esActiva = idLinea == lineaActivaId

            Box(
                modifier = Modifier
                    .size(if (esActiva) 40.dp else 34.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { onCambiarLinea(idLinea) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = numero.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (esActiva) 16.sp else 13.sp
                )
            }
        }
    }
}