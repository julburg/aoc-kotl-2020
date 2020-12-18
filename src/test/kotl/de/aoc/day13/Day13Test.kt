package de.aoc.day11

import de.aoc.day13.day13_a
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class Day13Test {

    @Test
    fun day11_a() {
        val input = Day13Test::class.java.getResource("/day13_input").readText()

        val result = day13_a(input)
        assertEquals(2324, result)
    }

    @Test
    fun day13_a_example_1() {
        val input = "939\n" +
                "7,13,x,x,59,x,31,19"

        val result = day13_a(input)
        assertEquals(295, result)
    }


}
