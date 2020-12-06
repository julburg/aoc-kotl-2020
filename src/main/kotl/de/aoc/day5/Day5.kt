package de.aoc.day5


fun day5_a(inputAsString: String): Int {
    return getSeatList(inputAsString).maxOrNull()!!
}

fun day5_b(inputAsString: String): Int {
    val seatList = getSeatList(inputAsString).sorted()
    return listOf(seatList.first()..seatList.last()).flatten().first { !seatList.contains(it) }
}


private fun getSeatList(inputAsString: String) = inputAsString.split("\n").map { calculate(it) }

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