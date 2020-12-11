package de.aoc.day6

import de.aoc.day7.day7_a
import de.aoc.day7.parseBag
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.assertFalse

class Day7Test {

    @Test
    fun day7_a() {
        val input = Day7Test::class.java.getResource("/day7_input").readText()

        val result = day7_a(input)
        assertEquals(192,result)
    }

    @Test
    fun day7_a_example_1() {
        val input = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags."

        val result = day7_a(input)
        assertEquals(result, 4)
    }


    @Test
    fun parseBag() {

        var bag = parseBag("light red bags contain 1 bright white bag, 2 muted yellow bags.")
        assertEquals("light red", bag.bagname)
        assertEquals(listOf("bright white", "muted yellow"), bag.containingBags)

        bag = parseBag("dotted black bags contain no other bags.")
        assertEquals("dotted black", bag.bagname)
        assertEquals(emptyList<String>(), bag.containingBags)

    }

    @Test
    fun containsOneOf() {

        val bag = parseBag("light red bags contain 1 bright white bag, 2 muted yellow bags.")
        assertFalse(bag.containsOneOf(listOf("blue red")))
        assertTrue(bag.containsOneOf(listOf("bright white")))
        assertTrue(bag.containsOneOf(listOf("muted yellow")))
    }

}