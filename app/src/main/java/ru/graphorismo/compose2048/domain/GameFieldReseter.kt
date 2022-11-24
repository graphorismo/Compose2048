package ru.graphorismo.compose2048.domain

import androidx.compose.runtime.mutableStateOf
import ru.graphorismo.compose2048.GameFieldState
import javax.inject.Inject

class GameFieldReseter @Inject constructor(var matrixWorker: MatrixWorker) {
    fun execute(){
        matrixWorker.matrix =
            mutableListOf(
                mutableListOf(2,0,0,0),
                mutableListOf(0,2,0,0),
                mutableListOf(2,0,0,0),
                mutableListOf(0,0,2,0)
            )

    }
}