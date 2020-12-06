package de.aoc.day6


fun day6_a(inputAsString: String): Int {
    val groups = inputAsString.split("\n\n")
    val groupsSplitted = groups.map { it.split("\n").map { it.split("").filter { it != "" } }.flatten() }
    return groupsSplitted.map { it.distinct().count() }.sum()
}

fun day6_b(inputAsString: String): Int {
    val groups = inputAsString.split("\n\n")
    val groupsSplitted = groups.map { it.split("\n").map { it.split("").filter { it != "" } } }
    return groupsSplitted.map { getAnswersInEveryGroup(it).count() }.sum()
}

fun getAnswersInEveryGroup(group: List<List<String>>): List<String> {
    val distinctValues = group.flatten().distinct()
    return distinctValues.filter { value -> group.all { it.contains(value) } }

}
