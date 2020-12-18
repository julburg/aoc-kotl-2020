package de.aoc.day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day14Test {

    @Test
    fun day14_a() {
        val input = Day14Test::class.java.getResource("/day14_input").readText()

        val result = day14_a(input)
        assertEquals(15172047086292, result)
    }

    @Test
    fun day14_a_example_1() {
        val input = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0"

        val result = day14_a(input)
        assertEquals(165, result)
    }

    @Test
    fun day14_b() {
        val input = Day14Test::class.java.getResource("/day14_input").readText()

        val result = day14_b(input)
        assertEquals(4197941339968, result)
    }


    @Test
    fun day14_b_example_1() {
        val input = "mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1"

        val result = day14_b(input)
        assertEquals(208, result)
    }

    @Test
    internal fun connectV1BitmaskWithValue() {
        var result = connectV1("000000000000000000000000000000001011", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals("000000000000000000000000000001001001", result)

        result = connectV1("000000000000000000000000000001100101", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals("000000000000000000000000000001100101", result)

        result = connectV1("000000000000000000000000000000000000", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals("000000000000000000000000000001000000", result)
    }

    @Test
    internal fun connectV2BitmaskWithValue() {
        var result = connectV2("000000000000000000000000000000101010", "000000000000000000000000000000X1001X")
        assertEquals("000000000000000000000000000000X1101X", result)

        result = connectV2("000000000000000000000000000000011010", "00000000000000000000000000000000X0XX")
        assertEquals("00000000000000000000000000000001X0XX", result)

    }


    @Test
    internal fun connectToX() {
        var result = connectToX("000000000000000000000000000000X1101X", "00")
        assertEquals("000000000000000000000000000000011010", result)

        result = connectToX("000000000000000000000000000000X1101X", "01")
        assertEquals("000000000000000000000000000000011011", result)
    }

    @Test
    internal fun toBinaryNumber() {
        assertEquals("000000000000000000000000000000001011", toBinaryNumber(11))
        assertEquals("000000000000000000000000000001100101", toBinaryNumber(101))
        assertEquals("000000000000000000000000000000000000", toBinaryNumber(0))
    }

    @Test
    internal fun parseInput() {

        val input = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[10] = 0"

        val instructions = parseInput(input)
        assertEquals(UpdateMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"), instructions.get(0))
        assertEquals(SetMemoryValue(8, "000000000000000000000000000000001011"), instructions.get(1))
        assertEquals(SetMemoryValue(7, "000000000000000000000000000001100101"), instructions.get(2))
        assertEquals(SetMemoryValue(10, "000000000000000000000000000000000000"), instructions.get(3))
    }

    @Test
    internal fun getBinaryCombinations() {
        val binaryCombinations = getBinaryCombinations(4)
        assertEquals(16, binaryCombinations.size)

    }
}
