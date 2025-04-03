package com.example.multiplicacion_de_matrices.classes

import android.opengl.Matrix
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

class MatrixState(
    initialSizeX: Int,
    initialSizeY: Int
) {
    private val _values = mutableStateMapOf<Pair<Int, Int>, String>()
    var sizeX by mutableStateOf(initialSizeX.coerceIn(1, 5)) // Límites 1-5
    var sizeY by mutableStateOf(initialSizeY.coerceIn(1, 5)) // Límites 1-5

    fun getValue(row: Int, col: Int): String =
        _values[row to col] ?: if (row < sizeY && col < sizeX) "" else ""

    fun updateValue(row: Int, col: Int, value: String) {
        if (value.isNotEmpty()) {
            _values[row to col] = value
        } else {
            _values.remove(row to col)
        }
    }

    fun toDoubleMatrix() = List(sizeY) { row ->
        List(sizeX) { col -> getValue(row, col).toDoubleOrNull() ?: 0.0 }
    }

    fun toCompactString(): String {
        return buildString {
            for (y in 0 until sizeY) {
                if (y > 0) append(";\n")
                for (x in 0 until sizeX) {
                    if (x > 0) append(" ")
                    append(getValue(y, x).takeUnless { it.isEmpty() } ?: "0")
                }
            }
        }
    }

    fun matrixToEqSys(m1: MatrixState, m2: MatrixState): String {
        return buildString {
            val variables = charArrayOf('x', 'y', 'z', 'u', 'v')
            for (y in 0 until m1.sizeY) {
                var allCellsEmpty = true
                for (x in 0 until m1.sizeX + 1) {
                    if (x < m1.sizeX) {
                        val value = m1.getValue(y, x)
                        if (value.isNotEmpty()) {
                            if (x > 0) {
                                if (value.toDouble() > 0.0 && !allCellsEmpty) append("+")
                            }
                            append(value)
                            append(variables[x])
                            allCellsEmpty = false
                        }
                    } else {
                        if (allCellsEmpty) {
                            append("0 = 0\n")
                        } else {
                            append(" = ")
                            append(m2.getValue(y, 0).takeUnless { it.isEmpty() } ?: "0")
                            append("\n")
                        }
                    }

                }
            }
        }
    }
}

