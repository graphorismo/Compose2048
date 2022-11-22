package ru.graphorismo.compose2048

import org.junit.Assert
import org.junit.Test
import ru.graphorismo.compose2048.domain.MoveDownPerformer

class MoveDownPerformerTest {

    // [ [0,0,0,0],     [ [0,0,0,0],
    //   [0,0,0,2],  ->   [0,0,0,2],
    //   [0,0,2,4],       [0,0,2,4],
    //   [0,2,4,8] ]      [0,2,4,8] ]
    @Test
    fun test1(){
        var field = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,2),
            mutableListOf(0,0,2,4),
            mutableListOf(0,2,4,8)
        )
        val performer = MoveDownPerformer()
        performer.execute(field)
        val expectedResult = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,2),
            mutableListOf(0,0,2,4),
            mutableListOf(0,2,4,8)
        )
        Assert.assertTrue(expectedResult == field)
    }

    // [ [4,0,0,0],     [ [0,0,0,0],
    //   [0,0,2,0],  ->   [0,0,0,0],
    //   [0,4,0,2],       [0,0,0,0],
    //   [4,4,2,2] ]      [8,8,4,4] ]
    @Test
    fun test2(){
        var field = mutableListOf(
            mutableListOf(4,0,0,0),
            mutableListOf(0,0,2,0),
            mutableListOf(0,4,0,2),
            mutableListOf(4,4,2,2)
        )
        val performer = MoveDownPerformer()
        performer.execute(field)
        val expectedResult = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(8,8,4,4)
        )
        Assert.assertTrue(expectedResult == field)
    }

    // [ [0,2,0,4],     [ [0,0,0,0],
    //   [2,2,4,2],  ->   [0,0,0,4],
    //   [2,2,0,0],       [2,0,0,2],
    //   [2,2,4,4] ]      [4,8,8,4] ]
    @Test
    fun test3(){
        var field = mutableListOf(
            mutableListOf(0,2,0,4),
            mutableListOf(2,2,4,2),
            mutableListOf(2,2,0,0),
            mutableListOf(2,2,4,4)
        )
        val performer = MoveDownPerformer()
        performer.execute(field)
        val expectedResult = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,4),
            mutableListOf(2,0,0,2),
            mutableListOf(4,8,8,4)
        )
        Assert.assertTrue(expectedResult == field)
    }
}
