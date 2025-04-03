package com.example.multiplicacion_de_matrices.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplicacion_de_matrices.classes.MatrixState
import com.example.multiplicacion_de_matrices.ui.screens.EqSys
import com.example.multiplicacion_de_matrices.ui.screens.Inverse
import com.example.multiplicacion_de_matrices.ui.screens.Multiply
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme

@Composable
fun MatrixInputGrid(
    matrixState: MatrixState,
    modifier: Modifier = Modifier,
    isMinimized: Boolean = false,
    isEquationSystem: Boolean = false,
    variables: CharArray = charArrayOf('x', 'y', 'z', 'u', 'v')
) {
    val currentSizeX = matrixState.sizeX
    val currentSizeY = matrixState.sizeY

    var text by remember { mutableStateOf("") }
    var textWidth by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        if (isMinimized) {
            // Vista minimizada
            Text(
                text = matrixState.toCompactString(),
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
            // Vista expandida
            repeat(currentSizeY) { row ->
                key(row) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(currentSizeX) { col ->
                            key(col) {
                                if (isEquationSystem) {
                                    BasicTextField(
                                        value = matrixState.getValue(row, col),
                                        onValueChange = { matrixState.updateValue(row, col, it) },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        modifier = Modifier
                                            .widthIn(min = 16.dp)
                                            .wrapContentWidth()
                                            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                                            .padding(8.dp),
                                        textStyle = LocalTextStyle.current.copy(
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onBackground,
                                        ),
                                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground)
                                    )
                                    Text(
                                        text = variables[col].toString(),
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .align(Alignment.Bottom)
                                            .padding(
                                                start = 4.dp,
                                                end = 8.dp,
                                                bottom = 10.dp
                                            )
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 1.dp, vertical = 1.dp)
                                            .weight(1f)
                                    ) {
                                        TextField(
                                            value = matrixState.getValue(row, col),
                                            onValueChange = { matrixState.updateValue(row, col, it) },
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number
                                            ),
                                            placeholder = { Text(
                                                text = "0",
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                                textAlign = TextAlign.Center
                                            ) },
                                            singleLine = true,
                                            colors = TextFieldDefaults.colors(
                                                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                                cursorColor = MaterialTheme.colorScheme.primary,
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                disabledContainerColor = Color.Transparent,
                                                focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .heightIn(min = 48.dp)
                                                .background(
                                                    Color.Transparent,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .then(
                                                    if (isEquationSystem) {
                                                        Modifier
                                                    } else {
                                                        Modifier.horizontalScroll(rememberScrollState())
                                                    }
                                                ),
                                            textStyle = LocalTextStyle.current.copy(
                                                fontSize = 14.sp,
                                                letterSpacing = (-0.2).sp,
                                                color = MaterialTheme.colorScheme.onBackground,
                                                textAlign = TextAlign.Left
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Multiply2Preview() {
    MultiplicacionDeMatricesTheme {
        EqSys(onNavigateToMenu = {})
    }
}