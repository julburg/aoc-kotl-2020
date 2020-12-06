package de.aoc.day4

import de.aoc.day5.calculate
import de.aoc.day5.day5_a
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day5Test {

    @Test
    fun day5_a() {
        val input = Day5Test::class.java.getResource("/day5_input").readText()

        val result = day5_a(input)
        assertEquals(result, 894)
    }

    @Test
    fun day5_a_example_1() {
        val input = "BFFFBBFRRR";

        val result = calculate(input)
        assertEquals(result, 567)
    }

    @Test
    fun day5_a_example_2() {
        val input = "FFFBBBFRRR";

        val result = calculate(input)
        assertEquals(result, 119)
    }

    @Test
    fun day5_a_example_3() {
        val input = "BBFFBBFRLL";

        val result = calculate(input)
        assertEquals(result, 820)
    }

}