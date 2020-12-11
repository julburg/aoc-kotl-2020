package de.aoc.day6

import de.aoc.day8.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class Day8Test {

    @Test
    fun day8_a() {
        val input = Day8Test::class.java.getResource("/day8_input").readText()

        val result = day8_a(input)
        assertEquals(1487, result)
    }

    @Test
    fun day8_a_example_1() {
        val input = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6"

        val result = day8_a(input)
        assertEquals(5, result)
    }

    @Test
    fun parseJumpInstruction() {
        val instruction = parseInstruction("jmp -30")
        assertEquals(instruction, Jump(-30))
    }

    @Test
    fun parseAccumulateInstruction() {
        val instruction = parseInstruction("acc +3")
        assertEquals(instruction, Accumulate(3))
    }

    @Test
    fun parseNoOperationInstruction() {
        val instruction = parseInstruction("nop +0")
        assertTrue(instruction is NoOperation)
    }

    @Test
    fun accumulate() {
        val accumulateInstruction = Accumulate(2)
        val (value, index, terminated) = accumulateInstruction.execute(ProgramState(0, 0, false))

        assertEquals(2, value)
        assertEquals(1, index)
        assertEquals(false, terminated)
    }

    @Test
    fun jumpTo() {
        val accumulateInstruction = Jump(2)
        val (value, index, terminated) = accumulateInstruction.execute(ProgramState(0, 0, false))

        assertEquals(0, value)
        assertEquals(2, index)
        assertEquals(false, terminated)
    }

    @Test
    fun noOperation() {
        val accumulateInstruction = NoOperation()
        val (value, index, terminated) = accumulateInstruction.execute(ProgramState(0, 0, false))

        assertEquals(0, value)
        assertEquals(1, index)
        assertEquals(false, terminated)
    }

    @Test
    fun terminateInstruction() {
        val accumulateInstruction = Accumulate(2)
        var programState = ProgramState(0, 0, false)
        programState = accumulateInstruction.execute(programState)
        val (value, index, terminated) = accumulateInstruction.execute(programState)

        assertEquals(2, value)
        assertEquals(1, index)
        assertEquals(true, terminated)
    }

}