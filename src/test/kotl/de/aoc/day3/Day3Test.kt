package de.aoc.day3

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day3Test {


    @Test
    fun day3_a() {
        val input = Day3Test::class.java.getResource("/day3_input").readText()

        val result = day3_a(input)
        assertEquals(result, 193)
    }

    @Test
    fun day3_a_example_1() {
        val input = "..##.......\n" +
                "#...#...#..\n" +
                ".#....#..#.\n" +
                "..#.#...#.#\n" +
                ".#...##..#.\n" +
                "..#.##.....\n" +
                ".#.#.#....#\n" +
                ".#........#\n" +
                "#.##...#...\n" +
                "#...##....#\n" +
                ".#..#...#.#";

        val result = day3_a(input)
        assertEquals(result, 7)
    }

    @Test
    fun day3_b() {
        val input = Day3Test::class.java.getResource("/day3_input").readText()

        val result = day3_b(input)
        assertEquals(result, 1355323200)
    }

    @Test
    fun day3_b_example_1() {
        val input = "..##.......\n" +
                "#...#...#..\n" +
                ".#....#..#.\n" +
                "..#.#...#.#\n" +
                ".#...##..#.\n" +
                "..#.##.....\n" +
                ".#.#.#....#\n" +
                ".#........#\n" +
                "#.##...#...\n" +
                "#...##....#\n" +
                ".#..#...#.#";

        val result = day3_b(input)
        assertEquals(result, 336)
    }

    @Test
    fun isTreeOnSquare_isFalse() {
        var treeRow = TreeRow("..##.")
        assertFalse(treeRow.isTreeOnSquare(0))

        treeRow = TreeRow("..##.")
        assertFalse(treeRow.isTreeOnSquare(6))
    }

    @Test
    fun isTreeOnSquare_isTrue() {
        var treeRow = TreeRow("..##.")
        assertTrue(treeRow.isTreeOnSquare(2))

        treeRow = TreeRow("..##.")
        assertTrue(treeRow.isTreeOnSquare(7))
    }
}