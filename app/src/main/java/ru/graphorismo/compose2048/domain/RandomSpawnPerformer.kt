package ru.graphorismo.compose2048.domain

import kotlin.random.Random

class RandomSpawnPerformer {

    var matrix : MutableList<MutableList<Int>>? = null
        set(value) {
            field = value
            throwIfMatrixIsNull()
            throwIfMatrixIsNotMatrix()
        }

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
        for (line in matrix!!){
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
        for (line in matrix!!){
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
        var wasSpaned = false
        while(wasSpaned != true){
            topLoop1@for (j in 0 until matrix!!.size){
                for (i in 0 until matrix!![j].size){
                    var roll: Float = Random.nextDouble(0.0, 1.0).toFloat()
                    if ( roll < spawnChance ){
                        matrix!![j][i] = randomNumber
                        wasSpaned = true
                        break@topLoop1
                    }
                }
            }
        }
    }

    private fun throwIfMatrixIsNotMatrix(){
        val linesLengths = matrix!!.map { it.size }
        val matrixWidth = linesLengths[0]
        if(linesLengths.any{it != matrixWidth } || matrix!!.size!=matrixWidth)
            throw RuntimeException("MovePerformersFacade cant handle not a square matrices")
    }

    private fun throwIfMatrixIsNull(){
        if(matrix == null){
            throw NullPointerException("RandomSpawnPerformer cant handle a null value")
        }
    }

    private fun throwIfNumberOfNumsToSpawnIsLessThenZero(){
        if (numberOfNumsToSpawn < 0){
            throw RuntimeException("RandomSpawnPerformer cant spawn less then zero numbers")
        }
    }
}