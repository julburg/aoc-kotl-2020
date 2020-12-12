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

fun day9_b(inputAsString: String, numberToCheck: Long): Long {
    val inputNumbers = inputAsString.split("\n").map { it.toLong() }

    for ((index, _) in inputNumbers.withIndex()) {
        val encryptionWeakness = getEncryptionWeaknessFor(index, inputNumbers, numberToCheck)
        if (encryptionWeakness.isNotEmpty()) {
            val max = encryptionWeakness.maxOrNull()!!
            val min = encryptionWeakness.minOrNull()!!
            return max + min
        }
    }

    throw Exception("Encryption weakness could not be found.")

}

fun getEncryptionWeaknessFor(index: Int, numbers: List<Long>, numberToCheck: Long): List<Long> {
    var sum = 0L
    val allMatchingNumbers = ArrayList<Long>()
    for (i in index..numbers.size) {
        if (sum == numberToCheck) {
            return allMatchingNumbers
        }
        if (sum > numberToCheck) {
            break
        }
        allMatchingNumbers.add(numbers[i])
        sum += numbers[i]
    }
    return emptyList()
}