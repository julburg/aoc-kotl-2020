package de.aoc.day1


fun day1_a(inputAsString: String): Int {

    val input = inputAsString.split("\n").map(String::toInt).toIntArray()

    for (firstNumber in input) {
        val secondNumber = input.findLast { inputNumber -> firstNumber + inputNumber == 2020 }
        if (secondNumber != null) {
            return firstNumber * secondNumber;
        }
    }

    throw Exception("Program terminated without result!")
}

fun day1_b(inputAsString: String): Int {

    val input = inputAsString.split("\n").map(String::toInt).toIntArray()

    for (firstNumber in input) {
        for (secondNumber in input) {
            val thirdNumber = input.findLast { inputNumber -> firstNumber + secondNumber + inputNumber == 2020 }
            if (thirdNumber != null) {
                return firstNumber * secondNumber * thirdNumber;
            }
        }
    }

    throw Exception("Program terminated without result!")
}
