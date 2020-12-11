package de.aoc.day8


fun day8_a(inputAsString: String): Int {

    val instructions = inputAsString.split("\n").map { parseInstruction(it) }

    var programState = ProgramState(0, 0, false)
    while (!programState.terminated) {
        programState = instructions[programState.index].execute(programState)
    }

    return programState.value;
}

abstract class Instruction {

    var wasExecuted = false

    fun execute(programState: ProgramState): ProgramState {
        if (wasExecuted) {
            return ProgramState(programState.value, programState.index, true);
        }
        wasExecuted = true
        return executeIt(programState)
    }

    protected abstract fun executeIt(programState: ProgramState): ProgramState
}

data class Accumulate(var number: Int) : Instruction() {
    override fun executeIt(programState: ProgramState): ProgramState {
        return ProgramState(programState.value + number, programState.index + 1, programState.terminated)
    }
}

data class Jump(var jumpPlaces: Int) : Instruction() {
    override fun executeIt(programState: ProgramState): ProgramState {
        return ProgramState(programState.value, programState.index + jumpPlaces, programState.terminated)
    }
}

class NoOperation : Instruction() {
    override fun executeIt(programState: ProgramState): ProgramState {
        return ProgramState(programState.value, programState.index + 1, programState.terminated)
    }
}

data class ProgramState(var value: Int, var index: Int, var terminated: Boolean)


fun parseInstruction(instructionAsString: String): Instruction {
    val (instructionName, value) = "(.*) (.*)".toRegex().find(instructionAsString)!!.destructured
    return when (instructionName) {
        "acc" -> Accumulate(value.toInt())
        "jmp" -> Jump(value.toInt())
        "nop" -> NoOperation()
        else -> {
            throw Exception("Instruction $instructionName not implemented")
        }
    }
}
