package com.example.multiplicacion_de_matrices.utils

import kotlin.math.abs

object MatrixOperations {

    fun calculateInverse(matrix: List<List<Double>>): List<List<Double>> {
        val n = matrix.size
        require(matrix.all { it.size == n }) { "La matriz debe ser cuadrada" }

        val determinant = calculateDeterminant(matrix)
        if (abs(determinant) < 1e-9) throw IllegalArgumentException("Matriz singular (determinante cero)")

        val adjugate = calculateAdjugate(matrix)

        return adjugate.map { row ->
            row.map { it / determinant }
        }
    }

    private fun calculateDeterminant(matrix: List<List<Double>>): Double {
        return when (matrix.size) {
            1 -> matrix[0][0]
            2 -> matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
            else -> {
                var det = 0.0
                for (i in matrix.indices) {
                    val minor = matrix.filterIndexes { rowIdx, _ -> rowIdx != 0 }
                        .map { row -> row.filterIndexes { idx, _ -> idx != i } }
                    det += matrix[0][i] * calculateDeterminant(minor) * if (i % 2 == 0) 1 else -1
                }
                det
            }
        }
    }

    private fun calculateAdjugate(matrix: List<List<Double>>): List<List<Double>> {
        val n = matrix.size

        if (n == 1) {
            return listOf(listOf(1.0))
        }

        return List(n) { i ->
            List(n) { j ->
                val minor = matrix.filterIndexes { rowIdx, _ -> rowIdx != i }
                    .map { row -> row.filterIndexes { colIdx, _ -> colIdx != j } }
                val sign = if ((i + j) % 2 == 0) 1 else -1
                calculateDeterminant(minor) * sign
            }
        }.transposed()
    }

    // Funciones de extensión
    private fun <T> List<List<T>>.transposed(): List<List<T>> {
        return List(this[0].size) { i ->
            List(this.size) { j -> this[j][i] }
        }
    }

    private fun <T> List<T>.filterIndexes(predicate: (Int, T) -> Boolean): List<T> {
        return this.withIndex()
            .filter { (index, value) -> predicate(index, value) }
            .map { it.value }
    }

    fun multiplyMatrixes(
        matrixA: List<List<Double>>,
        matrixB: List<List<Double>>,
    ): List<List<Double>> {

        // Validar que las matrices no estén vacías

        if (matrixA.isEmpty() || matrixB.isEmpty()) {
            throw IllegalArgumentException("Las matrices no pueden estar vacías")
        }

        // Validar que el número de columnas de la primera matriz sea
        // igual al número de filas de la segunda

        val columnasA = matrixA[0].size
        val filasA = matrixA.size
        val columnasB = matrixB[0].size
        val filasB = matrixB.size

        if (columnasA != filasB) {
            throw IllegalArgumentException(
                "El número de columnas de A (${columnasA}) " +
                "debe coincidir con el número de filas de B (${filasB})"
            )
        }

        // Crear matriz producto inicializada con valores en 0.0
        val product = MutableList(filasA) { MutableList(columnasB) { 0.0 } }

        // Algoritmo de multiplicación
        for (i in 0 until filasA) {
            for (j in 0 until columnasB) {
                var suma = 0.0
                for (k in 0 until columnasA) {
                    suma += matrixA[i][k] * matrixB[k][j]
                }
                product[i][j] = suma
            }
        }

        return product
    }
}