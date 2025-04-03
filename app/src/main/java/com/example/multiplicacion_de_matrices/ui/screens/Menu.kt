package com.example.multiplicacion_de_matrices.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplicacion_de_matrices.components.MenuButton
import com.example.multiplicacion_de_matrices.ui.theme.WhiteColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.tooling.preview.Preview
import com.example.multiplicacion_de_matrices.navigation.AppNavigation
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(
    onNavigateToInverse: () -> Unit,
    onNavigateToMultiply: () -> Unit,
    onNavigateToEqSys: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Aplicación") },
                // Icono de navegación (ej: botón "Atrás")
                navigationIcon = {
                    IconButton(onClick = { /* Acción al hacer clic */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                // Acciones en la barra (ej: menú)
                actions = {
                    IconButton(onClick = { /* Acción */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { /* Acción */ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Más opciones")
                    }
                }
            )
        }
    ) { innerPadding ->
        // Contenido principal de tu pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            MenuButton(
                onClick = {
                    onNavigateToMultiply()
                },
                textValue = "Multiplicación de matrices",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                paddingTop = 5.dp,
                paddingBottom = 5.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            MenuButton(
                onClick = {
                    onNavigateToInverse()
                },
                textValue = "Inversa de una matriz",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                paddingTop = 5.dp,
                paddingBottom = 5.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            MenuButton(
                onClick = {
                    onNavigateToEqSys()
                },
                textValue = "Sistema de ecuaciones",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                paddingTop = 5.dp,
                paddingBottom = 5.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiplicacionDeMatricesTheme {
        AppNavigation()
    }
}