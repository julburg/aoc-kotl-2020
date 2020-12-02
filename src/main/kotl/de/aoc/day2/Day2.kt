package de.aoc.day2


fun day2_a(inputAsString: String): Int {

    val passwords = inputAsString.split("\n").map { parse(it) };
    return passwords.filter { it.isValid_bySledRentalPlacePolicy() }.count();
}


fun day2_b(inputAsString: String): Int {

    val passwords = inputAsString.split("\n").map { parse(it) };
    return passwords.filter { it.isValid_byTobogganCorporatePolicy() }.count();
}


fun parse(input: String): Password {
    val regex = "(\\d*)-(\\d*) (\\w*): (\\w*)".toRegex()
    val (rangeStart, rangeEnd, letter, password) = regex.matchEntire(input)!!.destructured
    return Password(password, letter, IntRange(rangeStart.toInt(), rangeEnd.toInt()))
}

data class Password(var password: String, var letter: String, var range: IntRange) {

    fun isValid_bySledRentalPlacePolicy(): Boolean {
        val count = password.split("").filter { it.equals(letter) }.count();
        return range.contains(count);
    }

    fun isValid_byTobogganCorporatePolicy(): Boolean {
        val letter1Matches = password[range.first - 1].toString() == letter
        val letter2Matches = password[range.last - 1].toString() == letter
        return (letter1Matches && !letter2Matches) || (!letter1Matches && letter2Matches);
    }

}