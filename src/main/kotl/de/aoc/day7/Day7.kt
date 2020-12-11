package de.aoc.day7


fun day7_a(inputAsString: String): Int {

    val bags = inputAsString.split("\n").map { parseBag(it) }

    val bagsContainingGoldBags=ArrayList<Bag>()
    var bagsToCheck= listOf("shiny gold")
    while (bagsToCheck.isNotEmpty()){
        val bagsForBagsToCheck = bags.filter { it.containsOneOf(bagsToCheck) }
        bagsToCheck=bagsForBagsToCheck.map { it.bagname };
        bagsContainingGoldBags.addAll(bagsForBagsToCheck)
    }
    return bagsContainingGoldBags.distinct().size;
}

data class Bag(var bagname: String, var containingBags: List<String>){

    fun containsOneOf(bagsToContain: List<String>): Boolean {

        return bagsToContain.any{containingBags.contains(it)}
    }
}

fun parseBag(bagNameAsString: String): Bag {
    val bagName = parseBagName(bagNameAsString)
    val containingBags = parseContainingBagNames(bagNameAsString)
    return Bag(bagName, containingBags)
}

fun parseBagName(bagAsString: String): String {
    val (bagName) = "(.*) bags contain".toRegex().find(bagAsString)!!.destructured
    return bagName
}

fun parseContainingBagNames(bagAsString: String): List<String> {
    val findAll = "(\\d+ (\\w* \\w*) bag)".toRegex().findAll(bagAsString)
    return findAll.map { it.groupValues[2] }.toList()
}

