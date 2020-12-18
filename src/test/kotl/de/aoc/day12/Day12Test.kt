package de.aoc.day12

import de.aoc.day12.Direction.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day12Test {

    @Test
    fun day12_a() {
        val input = Day12Test::class.java.getResource("/day12_input").readText()

        val result = day12_a(input)
        assertEquals(845, result)
    }


    @Test
    fun day12_a_example_1() {
        val input = "F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11"

        val result = day12_a(input)
        assertEquals(25, result)
    }


    @Test
    internal fun parseActions() {
        val actions = parseActions(
                "F7\n" +
                        "R90")

        assertEquals(actions, listOf(Action("F", 7), Action("R", 90)))
    }

    @Test
    internal fun turnFerryRight() {
        val ferry = Ferry()
        ferry.turn("R", 90)
        assertEquals(ferry.facingDirection, SOUTH)
        ferry.turn("R", 180)
        assertEquals(ferry.facingDirection, NORTH)
    }

    @Test
    internal fun turnFerryLeft() {
        val ferry = Ferry()
        ferry.turn("L", 180)
        assertEquals(ferry.facingDirection, WEST)
        ferry.turn("L", 90)
        assertEquals(ferry.facingDirection, SOUTH)
    }

    @Test
    internal fun move_northSouth() {
        val ferry = Ferry()
        ferry.execute(Action("N", 5))
        assertEquals(ferry.northSouth, 5)

        ferry.execute(Action("S", 6))
        assertEquals(ferry.northSouth, -1)
    }

    @Test
    internal fun move_eastWest() {
        val ferry = Ferry()
        ferry.execute(Action("E", 5))
        assertEquals(ferry.eastWest, 5)

        ferry.execute(Action("W", 6))
        assertEquals(ferry.eastWest, -1)
    }

    @Test
    internal fun move_Forward() {
        val ferry = Ferry()
        ferry.execute(Action("F", 5))
        assertEquals(ferry.eastWest, 5)

        ferry.turn("L", 90)

        ferry.execute(Action("F", 5))
        assertEquals(ferry.northSouth, 5)
    }
}