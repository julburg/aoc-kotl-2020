package de.aoc.day11

import de.aoc.day15.day15_a
import de.aoc.day15.getIndexOfBeforeLastNumberSpoken
import de.aoc.day15.getInitialMemoryGameMap
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day15Test {

    @Test
    fun day15_a() {
        val input = "13,16,0,12,15,1"

        val result = day15_a(input)
        assertEquals(319, result)
    }

    @Test
    fun day15_a_example_1() {
        val input = "0,3,6"

        val result = day15_a(input)
        assertEquals(436, result)
    }



    @Test
    internal fun getInitialMemoryGameMap() {
        val input = "0,3,6"
        val initialMemoryGameMap = getInitialMemoryGameMap(input)
        assertEquals(hashMapOf(Pair(1, 0), Pair(2, 3), Pair(3, 6)), initialMemoryGameMap)
    }

    @Test
    internal fun getIndexOfBeforeLastNumberSpoken() {
        val memoryGameMap = hashMapOf(Pair(1, 0), Pair(2, 3), Pair(3, 6), Pair(4, 3))
        val indexOfBeforeLastNumberSpoken = getIndexOfBeforeLastNumberSpoken(memoryGameMap, 4, 3)
        assertEquals(indexOfBeforeLastNumberSpoken, 2)
    }


    @Test
    internal fun getIndexOfBeforeLastNumberSpoken_cannotBeFound() {
        val memoryGameMap = hashMapOf(Pair(1, 0), Pair(2, 3), Pair(3, 6))
        val indexOfBeforeLastNumberSpoken = getIndexOfBeforeLastNumberSpoken(memoryGameMap, 3, 6)
        assertEquals(indexOfBeforeLastNumberSpoken, null)
    }
}
