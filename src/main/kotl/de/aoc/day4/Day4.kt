package de.aoc.day4


fun day4_a(inputAsString: String): Int {
    return inputAsString.split("\n\n").map { Passport(it) }.filter { it.requiredFieldsSet() }.count();
}

fun day4_b(inputAsString: String): Int {
    return inputAsString.split("\n\n").map { Passport(it) }.filter { it.allFieldsAreValid() }.count();
}


data class Passport(var input: String) {
    val byr = parse("byr")?.toInt()
    val iyr = parse("iyr")?.toInt()
    val eyr = parse("eyr")?.toInt()
    val hgt = parse("hgt")?.let { Hgt(it) }
    val hcl = parse("hcl")?.let { Hcl(it) }
    val ecl = parse("ecl")?.let { Ecl(it) }
    val pid = parse("pid")?.let { Pid(it) }


    fun requiredFieldsSet(): Boolean {
        return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null
    }

    fun allFieldsAreValid(): Boolean {
        val byrValid = IntRange(1920, 2002).contains(byr)
        val iyrValid = IntRange(2010, 2020).contains(iyr)
        val eyrValid = IntRange(2020, 2030).contains(eyr)
        val hgtValid = hgt?.isValid() ?: run { false }
        val eclValid = ecl?.isValid() ?: run { false }
        val hclValid = hcl?.isValid() ?: run { false }
        val pidValid = pid?.isValid() ?: run { false }
        return byrValid && iyrValid && eyrValid && hgtValid && eclValid && hclValid && pidValid;

    }

    private fun parse(key: String): String? {
        val find = "$key:(\\w*\\d*\\S*[^\\s])".toRegex().find(input)
        if (find != null) {
            return find.groupValues[1];
        }
        return null
    }


}

data class Hcl(var input: String) {

    fun isValid(): Boolean {
        return "^#[0-9a-f]{6}\$".toRegex().matches(input)
    }
}

data class Pid(var input: String) {

    fun isValid(): Boolean {
        return "^[0-9]{9}\$".toRegex().matches(input)
    }
}

data class Ecl(var input: String) {
    var validElemts = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    fun isValid(): Boolean {
        return validElemts.contains(input)
    }
}

data class Hgt(var input: String) {
    var number: Int = 0
    var unit: String = ""

    init {
        val find = "(\\d*)(cm|in)".toRegex().find(input)
        if (find != null) {
            number = find.groupValues[1].toInt();
            unit = find.groupValues[2];
        }

    }

    fun isValid(): Boolean {

        if (unit == "cm") {
            return IntRange(150, 193).contains(number)
        }
        if (unit == "in") {
            return IntRange(59, 76).contains(number)
        }
        return false
    }
}