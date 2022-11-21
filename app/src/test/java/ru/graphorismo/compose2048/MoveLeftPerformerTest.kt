package ru.graphorismo.compose2048

import org.junit.Test
import org.junit.Assert.*
import ru.graphorismo.compose2048.domain.MoveLeftPerformer

class MoveLeftPerformerTest {

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
        val performer = MoveLeftPerformer()
        performer.execute(field)
        val expectedResult = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,2),
            mutableListOf(0,0,2,4),
            mutableListOf(0,2,4,8)
        )
        assertTrue(expectedResult == field)
    }

    // [ [0,0,2,2],     [ [0,0,0,4],
    //   [0,2,0,2],  ->   [0,0,0,4],
    //   [0,0,4,4],       [0,0,0,8],
    //   [4,0,0,4] ]      [0,0,0,8] ]
    @Test
    fun test2(){
        var field = mutableListOf(
            mutableListOf(0,0,2,2),
            mutableListOf(0,2,0,2),
            mutableListOf(0,0,4,4),
            mutableListOf(4,0,0,4)
        )
        val performer = MoveLeftPerformer()
        performer.execute(field)
        val expectedResult = mutableListOf(
            mutableListOf(0,0,0,4),
            mutableListOf(0,0,0,4),
            mutableListOf(0,0,0,8),
            mutableListOf(0,0,0,8)
        )
        assertTrue(expectedResult == field)
    }

    // [ [2,2,2,0],     [ [0,0,2,4],
    //   [2,2,2,2],  ->   [0,0,4,4],
    //   [4,0,4,0],       [0,0,0,8],
    //   [4,0,2,4] ]      [0,4,2,4] ]
    @Test
    fun test3(){
        var field = mutableListOf(
            mutableListOf(2,2,2,0),
            mutableListOf(2,2,2,2),
            mutableListOf(4,0,4,0),
            mutableListOf(4,0,2,4)
        )
        val performer = MoveLeftPerformer()
        performer.execute(field)
        val expectedResult = mutableListOf(
            mutableListOf(0,0,2,4),
            mutableListOf(0,0,0,8),
            mutableListOf(0,0,0,8),
            mutableListOf(0,4,2,4)
        )
        assertTrue(expectedResult == field)
    }
}