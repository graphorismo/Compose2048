package ru.graphorismo.compose2048

import org.junit.Test
import org.junit.Assert.*
import ru.graphorismo.compose2048.domain.MatrixWorker
import ru.graphorismo.compose2048.domain.RandomSpawnPerformer

class RandomSpawnPerformerTest() {

    @Test
    fun spawnTwoNumsAndAssertTheirCount(){
        val matrix = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0)
        )
        var randomSpawnPerformer = RandomSpawnPerformer(MatrixWorker())
        randomSpawnPerformer.numberOfNumsToSpawn = 2
        randomSpawnPerformer.matrixWorker.matrix = matrix
        randomSpawnPerformer.execute()
        val numberOfZeros = randomSpawnPerformer.matrixWorker.countZeros()
        assertTrue(numberOfZeros == 14)
    }

    @Test
    fun testThrowOnLessThenZeroNumbersToSpawn(){
        var randomSpawnPerformer = RandomSpawnPerformer(MatrixWorker())
        assertThrows(RuntimeException::class.java){
            randomSpawnPerformer.numberOfNumsToSpawn=-5
        }
    }

}