package de.aoc.day4

import java.util.regex.Pattern


fun day4_a(inputAsString: String): Int {
    return inputAsString.split("\n\n").map { Passport(it) }.filter { it.isValid() }.count();
}

data class Passport(var input: String) {
    val components = "(\\w*):".toRegex().findAll(input).map { it.destructured.component1() }.toList()


    fun isValid(): Boolean {
        return components.filter { it != "cid" }.count() == 7
    }

}

