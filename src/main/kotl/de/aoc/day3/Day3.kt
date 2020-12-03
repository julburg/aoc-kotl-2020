package de.aoc.day3


fun day3_a(inputAsString: String): Int {
    val slope = Slope(3, 1);

    val treeRows = inputAsString.split("\n").map { TreeRow(it) };
    return Forest(treeRows).countTreesFor(slope);

}


fun day3_b(inputAsString: String): Int {
    val treeRows = inputAsString.split("\n").map { TreeRow(it) };

    val forest = Forest(treeRows)
    val treesOnSlope1 = forest.countTreesFor(Slope(1, 1))
    val treesOnSlope2 = forest.countTreesFor(Slope(3, 1))
    val treesOnSlope3 = forest.countTreesFor(Slope(5, 1))
    val treesOnSlope4 = forest.countTreesFor(Slope(7, 1))
    val treesOnSlope5 = forest.countTreesFor(Slope(1, 2))

    return treesOnSlope1 * treesOnSlope2 * treesOnSlope3 * treesOnSlope4 * treesOnSlope5;

}

data class Slope(val right: Int, val down: Int)

data class Forest(var treeRows: List<TreeRow>) {

    fun countTreesFor(slope: Slope): Int {
        var squaresWithTree = 0;
        var square = 0;

        for (treeRow in getTreeRowsOnSlope(slope)) {
            if (treeRow.isTreeOnSquare(square)) {
                squaresWithTree++;
            }
            square += slope.right
        }
        return squaresWithTree
    }

    private fun getTreeRowsOnSlope(slope: Slope): List<TreeRow> {
        return treeRows.filterIndexed() { index, _ -> index % slope.down == 0 }
    }
}

data class TreeRow(var rowAsString: String) {

    var trees: List<Boolean> = rowAsString.split("").filter { it != "" }.map { it == "#" };

    fun isTreeOnSquare(square: Int): Boolean {
        val nextSquare = square % trees.size
        return trees[nextSquare];
    }

}

