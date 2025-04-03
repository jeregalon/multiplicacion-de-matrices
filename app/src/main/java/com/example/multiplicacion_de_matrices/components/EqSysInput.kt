package com.example.multiplicacion_de_matrices.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplicacion_de_matrices.classes.MatrixState
import com.example.multiplicacion_de_matrices.ui.screens.EqSys
import com.example.multiplicacion_de_matrices.ui.screens.EqSysPreview
import com.example.multiplicacion_de_matrices.ui.screens.Multiply
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme

@Composable
fun EqSysInput(
    coefficientsMatrix: MatrixState,
    rhsMatrix: MatrixState,
    isMinimized: Boolean = false,
    onToggleMinimize: () -> Unit = {}
) {
    // Botón para minimizar/mostrar
    Text(
        text = if (isMinimized) "\u25B6 Mostrar matriz" else "\u25BC Minimizar matriz",
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clickable { onToggleMinimize() }
    )
    
    if (isMinimized) {
        // Vista minimizada
        Text(
            text = coefficientsMatrix.matrixToEqSys(coefficientsMatrix, rhsMatrix),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha=0.3f),
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(RoundedCornerShape(4.dp))
                .padding(2.dp, top = 4.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp,
                letterSpacing = 0.5.sp,
                fontFamily = FontFamily.Monospace
            )
        )
    } else {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MatrixInputGrid(
                matrixState = coefficientsMatrix,
                modifier = Modifier,
                isMinimized = false,
                isEquationSystem = true,
                variables = charArrayOf('x', 'y', 'z', 'u', 'v')
            )
            Text(
                text = " = ",
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
            )
            MatrixInputGrid(
                matrixState = rhsMatrix,
                modifier = Modifier,
                isMinimized = false,
                isEquationSystem = true,
                variables = charArrayOf(' ')
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiplyPreview() {
    MultiplicacionDeMatricesTheme {
        EqSys(onNavigateToMenu = {})
    }
}