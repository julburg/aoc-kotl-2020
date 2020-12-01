package de.aoc.day1

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

class Day1Test {

    @Test
    fun day1_a_example_1() {
        val input = "1721\n" +
                "979\n" +
                "366\n" +
                "299\n" +
                "675\n" +
                "1456";

        val result = day1_a(input)
        assertEquals(result, 514579)
    }

    @Test
    fun day1_a() {
        val input = Day1Test::class.java.getResource("/day1_input").readText()

        val result = day1_a(input)
        assertEquals(result, 1010299)
    }

    @Test
    fun day1_b_example_1() {
        val input = "1721\n" +
                "979\n" +
                "366\n" +
                "299\n" +
                "675\n" +
                "1456";

        val result = day1_b(input)
        assertEquals(result, 241861950)
    }

    @Test
    fun day1_b() {
        val input = Day1Test::class.java.getResource("/day1_input").readText()

        val result = day1_b(input)
        assertEquals(result, 42140160)
    }

}