package de.aoc.day15


fun day15_a(inputAsString: String): Int {
    val memoryGameMap = getInitialMemoryGameMap(inputAsString)
    var lastNumberSpokenIndex = memoryGameMap.size
    while (lastNumberSpokenIndex != 2021) {
        val lastNumberSpoken = memoryGameMap[lastNumberSpokenIndex]!!
        val bevoreLastNumberSpokenIndex = getIndexOfBeforeLastNumberSpoken(memoryGameMap, lastNumberSpokenIndex, lastNumberSpoken)
        if (bevoreLastNumberSpokenIndex == null) {
            memoryGameMap.put(lastNumberSpokenIndex + 1, 0)
        } else {
            memoryGameMap.put(lastNumberSpokenIndex + 1, lastNumberSpokenIndex - bevoreLastNumberSpokenIndex)

        }
        lastNumberSpokenIndex = lastNumberSpokenIndex + 1
    }

    return memoryGameMap[lastNumberSpokenIndex-1]!!
}

fun getInitialMemoryGameMap(inputAsString: String): HashMap<Int, Int> {
    val numbers = inputAsString.split(",").filter { it.isNotBlank() }.map { it.toInt() }
    val memoryGameMap = HashMap<Int, Int>()
    memoryGameMap.putAll(numbers.mapIndexed { index, it -> index + 1 to it }.toMap())
    return memoryGameMap
}

fun getIndexOfBeforeLastNumberSpoken(memoryGameMap: HashMap<Int, Int>, lastNumberSpokenIndex: Int, lastNumberSpoken: Int): Int? {
    try {
        return memoryGameMap.filter { it.key != lastNumberSpokenIndex && it.value == lastNumberSpoken }.toSortedMap().lastKey()
    } catch (e: NoSuchElementException) {
        return null
    }

}

