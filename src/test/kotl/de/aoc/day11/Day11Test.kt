package de.aoc.day11

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class Day11Test {

    @Test
    fun day11_a() {
        val input = Day11Test::class.java.getResource("/day11_input").readText()

        val result = day11_a(input)
        assertEquals(2324, result)
    }

    @Test
    fun day11_a_example_1() {
        val input = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL"

        val result = day11_a(input)
        assertEquals(37, result)
    }

    @Test
    fun day11_b() {
        val input = Day11Test::class.java.getResource("/day11_input").readText()

        val result = day11_b(input)
        assertEquals(2324, result)
    }

    @Test
    fun day11_b_example_1() {
        val input = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL"

        val result = day11_b(input)
        assertEquals(26, result)
    }


    @Test
    fun getVisibleSeats() {
        val input = ".......#.\n" +
                "...#.....\n" +
                ".#.......\n" +
                ".........\n" +
                "..#L....#\n" +
                "....#....\n" +
                ".........\n" +
                "#........\n" +
                "...#....."

        val plane = parseSeatMap(input);
        val visibleSeats = plane.getVisibleSeats(SeatNumber(4, 3))
        assertTrue(visibleSeats.contains(SeatNumber(1, 3)))
        assertTrue(visibleSeats.contains(SeatNumber(0, 7)))
        assertTrue(visibleSeats.contains(SeatNumber(4, 8)))
        assertTrue(visibleSeats.contains(SeatNumber(5, 4)))
        assertTrue(visibleSeats.contains(SeatNumber(8, 3)))
        assertTrue(visibleSeats.contains(SeatNumber(4, 2)))
        assertTrue(visibleSeats.contains(SeatNumber(7, 0)))
        assertTrue(visibleSeats.contains(SeatNumber(2, 1)))
        assertEquals(visibleSeats.size, 8)
    }

    @Test
    internal fun parsePlaneMap() {
        val plane = parseSeatMap("L.#\n" +
                "LL.\n")
        assertTrue(plane.seats.keys.contains(SeatNumber(0, 0)))
        assertFalse(plane.seats.keys.contains(SeatNumber(0, 1)))
        assertTrue(plane.seats.keys.contains(SeatNumber(1, 0)))
        assertFalse(plane.seats.keys.contains(SeatNumber(1, 2)))
        assertEquals(plane.seats.keys.size, 4)
    }

    @Test
    internal fun getAdjacentSeats() {
        val plane = Plane(HashMap())
        val adjacentSeatNumbers = plane.getAdjacentSeatNumbers(SeatNumber(2, 2));
        val expectedSeats = listOf(SeatNumber(2, 3), SeatNumber(2, 1), SeatNumber(3, 2),
                SeatNumber(1, 2), SeatNumber(1, 3), SeatNumber(1, 1), SeatNumber(3, 3), SeatNumber(3, 1))
        assertEquals(adjacentSeatNumbers, expectedSeats)
    }

    @Test
    internal fun isSeatOccupied_isTrue() {
        val plane = Plane(hashMapOf(Pair(SeatNumber(2, 2), true)))
        assertTrue(plane.isSeatOccupied(SeatNumber(2, 2)))
    }

    @Test
    internal fun isSeatOccupied_isFalse() {
        val plane = Plane(hashMapOf(Pair(SeatNumber(2, 2), false)))
        assertFalse(plane.isSeatOccupied(SeatNumber(2, 2)))
    }

    @Test
    internal fun isSeatOccupied_isFalseBecauseSeatDoesNotExist() {
        val plane = Plane(HashMap())
        assertFalse(plane.isSeatOccupied(SeatNumber(2, 2)))
    }

}
