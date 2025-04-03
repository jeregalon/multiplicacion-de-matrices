package com.example.multiplicacion_de_matrices.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplicacion_de_matrices.navigation.AppNavigation
import com.example.multiplicacion_de_matrices.ui.screens.Inverse
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme
import com.example.multiplicacion_de_matrices.ui.theme.Primary
import com.example.multiplicacion_de_matrices.ui.theme.Secondary
import com.example.multiplicacion_de_matrices.ui.theme.WhiteColor

@Composable
fun InterfaceButton(
    onClick: () -> Unit,
    textValue: String = "Interface Button",
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontSize: TextUnit = 16.sp,
    enabled: Boolean = true,
    paddingTop: Dp = 10.dp,
    paddingBottom: Dp = 10.dp,
    paddingStart: Dp = 10.dp,
    paddingEnd: Dp = 10.dp,
    modifier: Modifier = Modifier
) {

    Button(onClick = onClick,
        modifier = modifier
            .padding(
                top = paddingTop,
                bottom = paddingBottom,
                start = paddingStart,
                end = paddingEnd
            )
            .background(
                color = if (enabled) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
                shape = RoundedCornerShape(24.dp)
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(text = textValue,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview(showBackground = true)
@Composable
fun InversePreview() {
    MultiplicacionDeMatricesTheme {
        Inverse(onNavigateToMenu = {})
    }
}