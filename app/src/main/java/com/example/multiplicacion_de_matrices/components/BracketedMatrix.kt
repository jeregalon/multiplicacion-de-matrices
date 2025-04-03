package com.example.multiplicacion_de_matrices.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multiplicacion_de_matrices.classes.MatrixState
import com.example.multiplicacion_de_matrices.ui.screens.EqSys
import com.example.multiplicacion_de_matrices.ui.screens.EqSysPreview
import com.example.multiplicacion_de_matrices.ui.screens.Inverse
import com.example.multiplicacion_de_matrices.ui.screens.Multiply
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme

@Composable
fun BracketedMatrix(
    matrixState: MatrixState,
    isMinimized: Boolean = false,
    onToggleMinimize: () -> Unit = {},
    bracketsColor: Color
) {
    // Botón para minimizar/mostrar
    Text(
        text = if (isMinimized) "\u25B6 Mostrar matriz" else "\u25BC Minimizar matriz",
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clickable { onToggleMinimize() }
    )

    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
    ) {

        // Corchete izquierdo
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    start = 8.dp,
                    top = 8.dp
                )
        ) {
            val strokeWidth = 2.dp.toPx()
            val color = bracketsColor

            // Línea vertical principal
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = strokeWidth
            )

            // Parte superior del corchete
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(8.dp.toPx(), 0f),
                strokeWidth = strokeWidth
            )

            // Parte inferior del corchete
            drawLine(
                color = color,
                start = Offset(0f, size.height),
                end = Offset(8.dp.toPx(), size.height),
                strokeWidth = strokeWidth
            )
        }

        // Contenido de la matriz
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            MatrixInputGrid(
                matrixState = matrixState,
                modifier = Modifier,
                isMinimized = isMinimized,
                isEquationSystem = false,
                variables = charArrayOf()
            )
        }

        // Corchete derecho
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    end = 4.dp,
                    top = 8.dp
                )
        ) {
            val strokeWidth = 2.dp.toPx()
            val color = bracketsColor

            // Línea vertical principal
            drawLine(
                color = color,
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )

            // Parte superior del corchete
            drawLine(
                color = color,
                start = Offset(size.width - 8.dp.toPx(), 0f),
                end = Offset(size.width, 0f),
                strokeWidth = strokeWidth
            )

            // Parte inferior del corchete
            drawLine(
                color = color,
                start = Offset(size.width - 8.dp.toPx(), size.height),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Multiply3Preview() {
    MultiplicacionDeMatricesTheme {
        Inverse(onNavigateToMenu = {})
    }
}