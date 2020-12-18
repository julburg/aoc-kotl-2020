package de.aoc.day12


fun day12_a(inputAsString: String): Int {

    val actions = parseActions(inputAsString)

    val ferry = Ferry()
    for (action in actions){
        ferry.execute(action)
    }
    return Math.abs(ferry.northSouth)+Math.abs(ferry.eastWest)
}


fun parseActions(inputAsString: String): ArrayList<Action> {

    val actionsAsString = inputAsString.split("\n")

    val actions = ArrayList<Action>()
    for (actionAsString in actionsAsString) {
        val (actionName, actionValue) = "(\\w)(\\d*)".toRegex().find(actionAsString)!!.destructured
        actions.add(Action(actionName, actionValue.toInt()))
    }
    return actions
}


data class Action(val name: String, val value: Int)


class Ferry() {
    var facingDirection = Direction.EAST
    var northSouth = 0
    var eastWest = 0


    fun execute(action: Action) {
        when (action.name) {
            "N" -> move(action.name, action.value)
            "S" -> move(action.name, action.value)
            "E" -> move(action.name, action.value)
            "W" -> move(action.name, action.value)
            "F" -> move(facingDirection.shortName, action.value)
            "R" -> turn(action.name, action.value)
            "L" -> turn(action.name, action.value)
            else -> {
                throw Exception("Action $action not implemented")
            }
        }

    }

    private fun move(action: String, value: Int) {
        when (action) {
            "N" -> northSouth = northSouth + value
            "S" -> northSouth = northSouth - value
            "E" -> eastWest = eastWest + value
            "W" -> eastWest = eastWest - value
            else -> {
                throw Exception("Action $action not implemented")
            }
        }
    }

    fun turn(direction: String, degrees: Int) {
        val steps = degrees / 90
        when (direction) {
            "R" -> facingDirection = Direction.values()[(facingDirection.ordinal + steps) % 4]
            "L" -> facingDirection = Direction.values()[(facingDirection.ordinal - steps + 4) % 4]

            else -> {
                throw Exception("Direction $direction not implemented")
            }
        }
    }
}

enum class Direction(var shortName: String) {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W")
}
