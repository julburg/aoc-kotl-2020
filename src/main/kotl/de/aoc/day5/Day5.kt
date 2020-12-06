package de.aoc.day5


fun day5_a(inputAsString: String): Int {
    return inputAsString.split("\n").map { calculate(it) }.maxOrNull ()!!
}

fun calculate(boardingPass: String): Int {
    val split = boardingPass.split("").filter { it!="" }
    val row = value(split[0], 64) + value(split[1], 32) + value(split[2], 16) + value(split[3], 8) + value(split[4], 4) + value(split[5], 2) + value(split[6], 1)
    val column = value(split[7], 4) + value(split[8], 2) + value(split[9], 1)
    return row * 8 + column;
}

fun value(designation: String, value: Int): Int {
    if (designation.equals("B") || designation.equals("R")) {
        return value
    } else {
        return 0
    }
}