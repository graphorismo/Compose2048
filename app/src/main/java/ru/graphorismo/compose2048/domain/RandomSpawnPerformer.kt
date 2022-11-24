package ru.graphorismo.compose2048.domain

import javax.inject.Inject
import kotlin.random.Random

class RandomSpawnPerformer @Inject constructor(var matrixWorker: MatrixWorker) {


    private var numbersInMatrix : MutableList<Int>? = null
    private var zerosInMatrixCount: Int = 0

    var numberOfNumsToSpawn = 0
        set(value) {
            field = value
            throwIfNumberOfNumsToSpawnIsLessThenZero()
        }

    fun execute(){
        rememberAllNumbersFromMatrixAndNumberTwoButNotZero()
        countZerosInMatrix()
        for(i in 0 until numberOfNumsToSpawn){
            spawnOneRandomNumberFromMatrixOnZeroOfMatrix()
        }
    }

    private fun rememberAllNumbersFromMatrixAndNumberTwoButNotZero(){
        numbersInMatrix = mutableListOf<Int>(2)
        for (line in matrixWorker.matrix){
            for (num in line){
                if ( (num in numbersInMatrix!!) == false ){
                    numbersInMatrix!!.add(num)
                }
            }
        }
        numbersInMatrix!!.remove(0)
    }

    private fun countZerosInMatrix(){
        zerosInMatrixCount = 0
        for (line in matrixWorker.matrix){
            for (num in line){
                if ( num  == 0 ){
                    zerosInMatrixCount += 1
                }
            }
        }
    }

    private fun spawnOneRandomNumberFromMatrixOnZeroOfMatrix(){
        val randomNumber = numbersInMatrix!!.random()
        val spawnChance = 1f / zerosInMatrixCount
        topLoop1@for(k in 0..9){
            for (j in 0 until matrixWorker.matrix.size){
                for (i in 0 until matrixWorker.matrix.size){
                    var roll: Float = Random.nextDouble(0.0, 1.0).toFloat()
                    if ( roll < spawnChance ){
                        matrixWorker.matrix[j][i] = randomNumber
                        break@topLoop1
                    }
                }
            }
        }
    }



    private fun throwIfNumberOfNumsToSpawnIsLessThenZero(){
        if (numberOfNumsToSpawn < 0){
            throw RuntimeException("RandomSpawnPerformer cant spawn less then zero numbers")
        }
    }
}