package de.aoc.day14


fun day14_a(inputAsString: String): Long {

    val instructions = parseInput(inputAsString)

    val program = Program()
    for (instruction in instructions) {
        instruction.executeV1(program)
    }
    return program.sumMemoryValues()
}

fun day14_b(inputAsString: String): Long {

    val instructions = parseInput(inputAsString)

    val program = Program()
    for (instruction in instructions) {
        instruction.executeV2(program)
    }
    return program.sumMemoryValues()
}


class Program {
    var mask: String = ""
    var memoryValues = HashMap<Long, String>()

    fun sumMemoryValues(): Long {
        return memoryValues.values.map { it.toLongOrNull(2)!! }.sum()
    }
}

interface Instruction {
    fun executeV1(program: Program)
    fun executeV2(program: Program)
}

data class UpdateMask(val mask: String) : Instruction {

    override fun executeV1(program: Program) {
        program.mask = mask
    }

    override fun executeV2(program: Program) {
        program.mask = mask
    }
}

data class SetMemoryValue(val memoryAddress: Long, val value: String) : Instruction {
    override fun executeV1(program: Program) {
        val mask = program.mask
        program.memoryValues[memoryAddress] = connectV1(value, mask)
    }

    override fun executeV2(program: Program) {
        val mask = program.mask
        val addressAsBinary = toBinaryNumber(memoryAddress)
        val addressToMask = connectV2(addressAsBinary, mask)

        val countFloating = addressToMask.split("").filter { it == "X" }.count()
        val binaryCombinations = getBinaryCombinations(countFloating)
        for (binaryCombination in binaryCombinations) {
            val newAddressBinary = connectToX(addressToMask, binaryCombination);
            val newAddress = newAddressBinary.toLong(2)
            program.memoryValues[newAddress] = value;
        }
    }
}

fun connectV1(valueAsString: String, bitmaskAsString: String): String {
    val bitmask = bitmaskAsString.split("").filter { it.isNotBlank() }
    val values = valueAsString.split("").filter { it.isNotBlank() }

    var result = ""
    for ((index, value) in values.withIndex()) {
        val bitmaskAtIndex = bitmask[index]
        if (bitmaskAtIndex == "X") {
            result += value
        } else {
            result += bitmaskAtIndex
        }
    }
    return result
}


fun connectV2(addressAsString: String, bitmaskAsString: String): String {
    val bitmask = bitmaskAsString.split("").filter { it.isNotBlank() }
    val addressValues = addressAsString.split("").filter { it.isNotBlank() }

    var result = ""
    for ((index, address) in addressValues.withIndex()) {
        val bitmaskAtIndex = bitmask[index]
        if (bitmaskAtIndex == "0") {
            result += address
        } else if (bitmaskAtIndex == "1") {
            result += 1
        } else {
            result += "X"
        }
    }
    return result
}


fun connectToX(addressAsString: String, combinationAsString: String): String {
    val combination = combinationAsString.split("").filter { it.isNotBlank() }
    val addressValues = addressAsString.split("").filter { it.isNotBlank() }

    var result = ""
    var indexXValues = 0
    for (address in addressValues) {
        if (address == "X") {
            result += combination[indexXValues]
            indexXValues += 1
        } else {
            result += address
        }
    }
    return result
}

fun getBinaryCombinations(count: Int): ArrayList<String> {
    val combinations = ArrayList<String>()
    val length = Math.pow(2.0, count.toDouble()).toInt()
    for (number in 0 until length) {
        combinations.add(Integer.toBinaryString(number).padStart(count, '0'))
    }
    return combinations;
}

fun toBinaryNumber(number: Long): String {
    return number.toString(2).padStart(36, '0')
}

fun parseInput(inputAsString: String): ArrayList<Instruction> {

    val input = inputAsString.split("\n")

    val instructions = ArrayList<Instruction>()
    for (inputValue in input) {
        if (inputValue.contains("mask")) {
            val (mask) = "mask = (\\w*)".toRegex().find(inputValue)!!.destructured
            instructions.add(UpdateMask(mask))
        } else {
            val (memoryAddress, memoryValue) = "mem\\[(\\d*)\\] = (\\d*)".toRegex().find(inputValue)!!.destructured
            instructions.add(SetMemoryValue(memoryAddress.toLong(), toBinaryNumber(memoryValue.toLong())))
        }
    }

    return instructions
}