package ru.graphorismo.compose2048.domain.move_performers

import javax.inject.Inject

class MovePerformersFacade @Inject constructor(
    private var moveUpPerformer: MoveUpPerformer,
    private var moveDownPerformer: MoveDownPerformer,
    private var moveLeftPerformer: MoveLeftPerformer,
    private var moveRightPerformer: MoveRightPerformer
) {

    fun performMoveUp(matrix: MutableList<MutableList<Int>>){
        throwIfNotMatrix(matrix)
        moveUpPerformer.execute(matrix)
    }

    fun performMoveDown(matrix: MutableList<MutableList<Int>>){
        throwIfNotMatrix(matrix)
        moveDownPerformer.execute(matrix)
    }

    fun performMoveLeft(matrix: MutableList<MutableList<Int>>){
        throwIfNotMatrix(matrix)
        moveLeftPerformer.execute(matrix)
    }

    fun performMoveRight(matrix: MutableList<MutableList<Int>>){
        throwIfNotMatrix(matrix)
        moveRightPerformer.execute(matrix)
    }

    private fun throwIfNotMatrix(matrix: MutableList<MutableList<Int>>){
        val linesLengths = matrix.map { it.size }
        val matrixWidth = linesLengths[0]
        if(linesLengths.any{it != matrixWidth } || matrix.size!=matrixWidth)
            throw RuntimeException("MovePerformersFacade cant handle not a square matrices")
    }
}