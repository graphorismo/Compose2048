package ru.graphorismo.compose2048

import org.junit.Assert
import org.junit.Test
import ru.graphorismo.compose2048.domain.MatrixWorker
import ru.graphorismo.compose2048.domain.RandomSpawnPerformer

class MatrixWorkerTest {

    @Test
    fun testThrowOnNotAMatrix(){
        val notAMatrix = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0),
        )
        var matrixWorker = MatrixWorker()
        Assert.assertThrows(RuntimeException::class.java) {
            matrixWorker.matrix = notAMatrix
        }
    }
}