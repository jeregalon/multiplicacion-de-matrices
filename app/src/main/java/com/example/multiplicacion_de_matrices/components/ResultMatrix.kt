package com.example.multiplicacion_de_matrices.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResultMatrix(
    matrix: List<List<Double>>,
    modifier: Modifier = Modifier,
    textColor: Color = Color.DarkGray,
    decimalFormat: String = "%.4f"
) {
    Column(
        modifier = modifier
    ) {
        Text("Resultado:",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(4.dp))

        matrix.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { value ->
                    Text(
                        text = decimalFormat.format(value),
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(color = textColor)
                    )
                }
            }
        }
    }
}