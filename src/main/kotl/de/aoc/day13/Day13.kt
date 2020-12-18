package de.aoc.day13


fun day13_a(inputAsString: String): Int {
    val inputSplitted = inputAsString.split("\n");
    val earliestDepartureTime = inputSplitted[0].toInt()
    val busIds = inputSplitted[1].split(",").filter { it.isNotBlank() && it!="x" }.map { it.toInt() }

    val busDepartureTimes = HashMap<Int, Int>()
    for (busId in busIds) {
        val nextDepartureTime = earliestDepartureTime - (earliestDepartureTime % busId) + busId
        busDepartureTimes[busId] = nextDepartureTime
    }
    val nextBusDepartureTime = busDepartureTimes.minByOrNull { it.value }!!
    val waitingTime= nextBusDepartureTime.value-earliestDepartureTime

    return nextBusDepartureTime.key*waitingTime
}
