package de.aoc.day6

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day6Test {

    @Test
    fun day6_a() {
        val input = Day6Test::class.java.getResource("/day6_input").readText()

        val result = day6_a(input)
        assertEquals(result, 6662)
    }

    @Test
    fun day6_b_example_1() {
        val input = "abc\n" +
                "\n" +
                "        a\n" +
                "        b\n" +
                "        c\n" +
                "\n" +
                "        ab\n" +
                "        ac\n" +
                "\n" +
                "        a\n" +
                "        a\n" +
                "        a\n" +
                "        a\n" +
                "\n" +
                "        b"

        val result = day6_b(input)
        assertEquals(result, 6)
    }

    @Test
    fun day6_a_example_1() {
        val input = "abc\n" +
                "\n" +
                "        a\n" +
                "        b\n" +
                "        c\n" +
                "\n" +
                "        ab\n" +
                "        ac\n" +
                "\n" +
                "        a\n" +
                "        a\n" +
                "        a\n" +
                "        a\n" +
                "\n" +
                "        b"

        val result = day6_a(input)
        assertEquals(result, 11)
    }


}