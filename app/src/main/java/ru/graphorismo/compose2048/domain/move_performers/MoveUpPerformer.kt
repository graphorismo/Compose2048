package ru.graphorismo.compose2048.domain.move_performers

import javax.inject.Inject

class MoveUpPerformer @Inject constructor() {
    fun execute(matrix: MutableList<MutableList<Int>>) {
        val matrixWidth = matrix[0].size
        val matrixHeight = matrix.size
        for (i in 0 until matrixWidth) {
            var somethingWasDone = true
            while (somethingWasDone) {
                somethingWasDone = false
                for (j in 1 until matrixHeight) {
                    if (matrix[j - 1][i] == 0 && matrix[j][i] != 0) {
                        matrix[j - 1][i] = matrix[j][i]
                        matrix[j][i] = 0
                        somethingWasDone = true
                    } else if (matrix[j][i] == matrix[j - 1][i] && matrix[j - 1][i] != 0) {
                        matrix[j][i] = 0
                        matrix[j - 1][i] *= 2
                        somethingWasDone = true
                    }

                }
            }
        }

    }
}