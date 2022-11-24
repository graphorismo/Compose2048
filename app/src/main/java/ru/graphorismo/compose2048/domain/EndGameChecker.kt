package ru.graphorismo.compose2048.domain

import javax.inject.Inject

class EndGameChecker @Inject constructor(var matrixWorker: MatrixWorker){

    var isEndGame = false
        private set

    fun execute(){
        isEndGame = matrixWorker.countZeros() == 0
    }
}