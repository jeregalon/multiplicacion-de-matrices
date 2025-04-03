package com.example.multiplicacion_de_matrices.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import com.example.multiplicacion_de_matrices.classes.MatrixState
import com.example.multiplicacion_de_matrices.components.BracketedMatrix
import com.example.multiplicacion_de_matrices.components.ResultMatrix
import com.example.multiplicacion_de_matrices.utils.MatrixOperations
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.unit.sp
import com.example.multiplicacion_de_matrices.components.InterfaceButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inverse(
    onNavigateToMenu: () -> Unit,
) {
    val matrixState = remember { MatrixState(3, 3) }

    var matrixMinimized by remember { mutableStateOf(false) }
    var inverseMatrix by remember { mutableStateOf<List<List<Double>>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hallar Inversa") },
                // Icono de navegación (ej: botón "Atrás")
                navigationIcon = {
                    IconButton(onClick = { onNavigateToMenu() }) {
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
        Text(
            text = "Contenido de la app",
            modifier = Modifier.padding(innerPadding)
        )

        // Estructura de la UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cuadrícula de TextFields

            BracketedMatrix(
                matrixState = matrixState,
                isMinimized = matrixMinimized,
                onToggleMinimize = { matrixMinimized = !matrixMinimized },
                bracketsColor = MaterialTheme.colorScheme.onBackground
            )

            // Botones de control (agregar y quitar filas/columnas)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InterfaceButton(
                    onClick = { if (matrixState.sizeX < 5) matrixState.sizeX++; matrixState.sizeY++ },
                    enabled = matrixState.sizeX < 5,
                    textValue = "Agregar fila/columna",
                    modifier = Modifier
                        .weight(1f)
                )
                InterfaceButton(
                    onClick = { if (matrixState.sizeX > 1) matrixState.sizeX--; matrixState.sizeY-- },
                    enabled = matrixState.sizeX > 1,
                    textValue = "Quitar fila/columna",
                    modifier = Modifier
                        .weight(1f)
                )
            }

            // Botón de Calcular Inversa
            InterfaceButton(
                onClick = {
                    try {
                        inverseMatrix = MatrixOperations.calculateInverse(matrixState.toDoubleMatrix())
                        errorMessage = null
                    } catch (e: Exception) {
                        errorMessage = when (e) {
                            is IllegalArgumentException -> e.message ?: "Error en cálculo"
                            else -> "Error: ${e.message}"
                        }
                        inverseMatrix = null
                    }
                },
                textValue = "Calcular inversa",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp
            )

            // Mostrar resultados o errores
            inverseMatrix?.let { matrix ->
                ResultMatrix(
                    matrix = matrix,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textColor = MaterialTheme.colorScheme.onSurface
                )
            }

            errorMessage?.let { msg ->
                Text(
                    text = msg,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InversePreview() {
    MultiplicacionDeMatricesTheme {
        Inverse(onNavigateToMenu = {})
    }
}