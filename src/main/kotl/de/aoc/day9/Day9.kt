package de.aoc.day9


fun day9_a(inputAsString: String, preamble: Int): Long {
    val inputNumbers = inputAsString.split("\n").map { it.toLong() }

    for (index in preamble until inputNumbers.size) {
        val lastNumbers = inputNumbers.subList(index - preamble, index)
        val numberToCheck = inputNumbers[index]
        val valid = isValid(numberToCheck, lastNumbers)
        if (!valid) {
            return numberToCheck
        }
    }
    throw Exception("All numbers were valid")
}


fun isValid(numberToCheck: Long, lastNumbers: List<Long>): Boolean {
    for (number in lastNumbers.filter { it < numberToCheck }) {
        val diff = numberToCheck - number
        if (lastNumbers.contains(diff)) {
            return true
        }
    }
    return false;
}