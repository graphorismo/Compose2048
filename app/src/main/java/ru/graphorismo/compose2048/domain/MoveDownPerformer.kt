package ru.graphorismo.compose2048.domain

import androidx.core.util.rangeTo

class MoveDownPerformer {

    fun execute(matrix: MutableList<MutableList<Int>>){
        var matrixWidth = matrix[0].size
        var matrixHeight = matrix.size
        for (i in 0 until matrixWidth){
            var somethingWasDone = true
            while (somethingWasDone){
                somethingWasDone = false
                for (j in (matrixHeight-2) downTo 0){
                    if(matrix[j+1][i]==0 && matrix[j][i] != 0){
                        matrix[j+1][i] = matrix[j][i]
                        matrix[j][i] = 0
                        somethingWasDone = true
                    }else if (matrix[j][i] == matrix[j+1][i]  && matrix[j+1][i]!=0){
                        matrix[j][i] = 0
                        matrix[j+1][i] *= 2
                        somethingWasDone = true
                    }

                }
            }
        }


    }

}