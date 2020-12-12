package de.aoc.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class Day9Test {

    @Test
    fun day9_a() {
        val input = Day9Test::class.java.getResource("/day9_input").readText()

        val result = day9_a(input, 25)
        assertEquals(29221323, result)
    }

    @Test
    fun day9_a_example_1() {
        val input = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"

        val result = day9_a(input, 5)
        assertEquals(127, result)
    }

    @Test
    fun day9b() {
        val input = Day9Test::class.java.getResource("/day9_input").readText()

        val result = day9_b(input, 29221323)
        assertEquals(4389369, result)
    }

    @Test
    fun day9_b_example_1() {
        val input = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"

        val result = day9_b(input, 127)
        assertEquals(62, result)
    }

    @Test
    fun isValid_isTrue() {

        assertTrue(isValid(40, listOf(35, 20, 15, 25, 47)))
        assertTrue(isValid(62, listOf(20, 15, 25, 47, 40)))
        assertTrue(isValid(55, listOf(15, 25, 47, 40, 62)))
    }

    @Test
    fun isValid_isFalse() {

        assertFalse(isValid(127, listOf(95, 102, 117, 150, 182)))
    }

    @Test
    fun encryptionWeakness() {

        assertEquals(getEncryptionWeaknessFor(0, listOf(35, 20, 15, 25, 47, 40), 127), emptyList())
        assertEquals(getEncryptionWeaknessFor(1, listOf(35, 20, 15, 25, 47, 40), 127), emptyList())
        assertEquals(listOf(15L, 25L, 47L, 40L), getEncryptionWeaknessFor(2, listOf(35, 20, 15, 25, 47, 40), 127))
    }
}