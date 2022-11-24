package ru.graphorismo.compose2048.domain

import javax.inject.Inject

class MatrixWorker @Inject constructor() {

    var matrix : MutableList<MutableList<Int>> = mutableListOf()
        set(value) {
            field = value
            throwIfMatrixIsNotMatrix()
        }

    private fun throwIfMatrixIsNotMatrix(){
        val linesLengths = matrix!!.map { it.size }
        val matrixWidth = linesLengths[0]
        if(linesLengths.any{it != matrixWidth } || matrix!!.size!=matrixWidth)
            throw RuntimeException("MovePerformersFacade cant handle not a square matrices")
    }

    fun countZeros(): Int{
        var zerosInMatrixCount = 0
        for (line in matrix){
            for (num in line){
                if ( num  == 0 ){
                    zerosInMatrixCount += 1
                }
            }
        }
        return zerosInMatrixCount
    }


}