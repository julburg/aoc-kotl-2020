package de.aoc.day6


fun day6_a(inputAsString: String): Int {
    val groups = inputAsString.split("\n\n")
    return groups.map { countDistinctAnswers(it) }.sum()
}

fun day6_b(inputAsString: String): Int {
    return 0
}


fun countDistinctAnswers(groupAnswer: String): Int {
    return groupAnswer.split("\n").map { it.split("").map { it.trim() }.filter { it!="" } }.flatten().distinct().count()
}
