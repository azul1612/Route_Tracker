package com.example.routetracker.presentation.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.example.routetracker.presentation.common.MenuSuperior

@Composable
fun PerfilScreen(
    onCerrarSesion: () -> Unit,
    onVolver: () -> Unit,
    onIrInicio: () -> Unit = {},
    onMapa: (() -> Unit)? = null
) {
    val usuarioActual = FirebaseAuth.getInstance().currentUser

    Column(modifier = Modifier.fillMaxSize()) {
        MenuSuperior(
            onPerfil = {},
            onInicio = onIrInicio,
            onAtras = onVolver,
            onMapa = onMapa,
            perfilActivo = true
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(96.dp),
                tint = Color(0xFFC1121F)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = usuarioActual?.email ?: "Usuario sin correo registrado",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Miembro de Route Tracker",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    onCerrarSesion()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC1121F))
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}