package de.aoc.day7


fun day7_a(inputAsString: String): Int {

    val bags = inputAsString.split("\n").map { parseBag(it) }

    val bagsContainingGoldBags = ArrayList<Bag>()
    var bagsToCheck = listOf("shiny gold")
    while (bagsToCheck.isNotEmpty()) {
        val bagsForBagsToCheck = bags.filter { it.containsOneOf(bagsToCheck) }
        bagsToCheck = bagsForBagsToCheck.map { it.bagname };
        bagsContainingGoldBags.addAll(bagsForBagsToCheck)
    }
    return bagsContainingGoldBags.distinct().size;
}

fun day7_b(inputAsString: String): Int {

    val bags = inputAsString.split("\n").map { parseBag(it) }
    val shinyGoldBag = bags.first { it.bagname == "shiny gold" }

    return countBagsInside(shinyGoldBag, bags) - 1;
}


fun countBagsInside(bag: Bag, bags: List<Bag>): Int {
    if (bag.containingBags.isEmpty()) {
        return 1
    }

    val bagsInside = bag.containingBags.map { countBagsInside(get(bags, it.bagname), bags) * it.count }.sum()
    return bagsInside + 1

}

private fun get(bags: List<Bag>, bagName: String) = bags.first { it.bagname == bagName }

data class Bag(var bagname: String, var containingBags: List<ContainingBag>) {

    fun containsOneOf(bagsToContain: List<String>): Boolean {

        return bagsToContain.any { containingBags.map { it.bagname }.contains(it) }
    }
    
}

data class ContainingBag(var bagname: String, var count: Int)

fun parseBag(bagNameAsString: String): Bag {
    val bagName = parseBagName(bagNameAsString)
    val containingBags = parseContainingBagNames(bagNameAsString)
    return Bag(bagName, containingBags)
}

fun parseBagName(bagAsString: String): String {
    val (bagName) = "(.*) bags contain".toRegex().find(bagAsString)!!.destructured
    return bagName
}

fun parseContainingBagNames(bagAsString: String): List<ContainingBag> {
    val findAll = "((\\d+) (\\w* \\w*) bag)".toRegex().findAll(bagAsString)
    return findAll.map { ContainingBag(it.groupValues[3], it.groupValues[2].toInt()) }.toList()
}

