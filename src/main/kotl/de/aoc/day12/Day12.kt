package de.aoc.day12

import java.awt.Point
import kotlin.math.abs
import kotlin.math.roundToInt


fun day12_a(inputAsString: String): Int {

    val actions = parseActions(inputAsString)

    val ferry = Ferry()
    for (action in actions) {
        ferry.execute(action)
    }
    return Math.abs(ferry.northSouth) + Math.abs(ferry.eastWest)
}


fun day12_b(inputAsString: String): Int {

    val actions = parseActions(inputAsString)

    val wayPoint = WayPoint()
    for (action in actions) {
        wayPoint.execute(action)
    }

    return Math.abs(wayPoint.ferry.northSouth) + Math.abs(wayPoint.ferry.eastWest)
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

open class Moveable(var northSouth: Int, var eastWest: Int) {

}

class WayPoint : Moveable(1, 10) {

    val ferry = Ferry()

    fun execute(action: Action) {
        when (action.name) {
            "N" -> move(action.name, action.value, this)
            "S" -> move(action.name, action.value, this)
            "E" -> move(action.name, action.value, this)
            "W" -> move(action.name, action.value, this)
            "F" -> {
                moveFerryForwardByWaypoint(action)
            }
            "R" -> rotate(action.value)
            "L" -> rotate(-action.value)

            else -> {
                throw Exception("Action $action not implemented")
            }
        }

    }

    fun moveFerryForwardByWaypoint(action: Action) {
        move(getDiectionNorthSouth().shortName, abs(northSouth) * action.value, ferry)
        move(getDiectionEastWest().shortName, abs(eastWest) * action.value, ferry)
    }

    fun getDiectionNorthSouth(): Direction {
        if (northSouth > 0) {
            return Direction.NORTH
        }
        return Direction.SOUTH
    }

    fun getDiectionEastWest(): Direction {
        if (eastWest > 0) {
            return Direction.EAST
        }
        return Direction.WEST
    }

    fun rotate(degrees: Int) {
        val point = Point(northSouth, eastWest)

        //copied from stackoverflow
        val angle = Math.toRadians(degrees.toDouble())
        val x2 = point.x * Math.cos(angle) - point.y * Math.sin(angle)
        val y2 = point.x * Math.sin(angle) + point.y * Math.cos(angle)

        northSouth = x2.roundToInt()
        eastWest = y2.roundToInt()
    }

}

class Ferry : Moveable(0, 0) {
    var facingDirection = Direction.EAST


    fun execute(action: Action) {
        when (action.name) {
            "N" -> move(action.name, action.value, this)
            "S" -> move(action.name, action.value, this)
            "E" -> move(action.name, action.value, this)
            "W" -> move(action.name, action.value, this)
            "F" -> move(facingDirection.shortName, action.value, this)
            "R" -> turn(action.name, action.value)
            "L" -> turn(action.name, action.value)
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

private fun move(action: String, value: Int, moveabale: Moveable) {
    when (action) {
        "N" -> moveabale.northSouth = moveabale.northSouth + value
        "S" -> moveabale.northSouth = moveabale.northSouth - value
        "E" -> moveabale.eastWest = moveabale.eastWest + value
        "W" -> moveabale.eastWest = moveabale.eastWest - value
        else -> {
            throw Exception("Action $action not implemented")
        }
    }
}


enum class Direction(var shortName: String) {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W")
}
