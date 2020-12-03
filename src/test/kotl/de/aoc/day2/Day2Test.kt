package de.aoc.day2

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day2Test {

    @Test
    fun day2_a_example_1() {
        val input = "1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc";

        val result = day2_a(input)
        assertEquals(result, 2)
    }

    @Test
    fun day2_a() {
        val input = Day2Test::class.java.getResource("/day2_input").readText()

        val result = day2_a(input)
        assertEquals(result, 396)
    }

    @Test
    fun day2_b_example_1() {
        val input = "1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc";

        val result = day2_b(input)
        assertEquals(result, 1)
    }

    @Test
    fun day2_b() {
        val input = Day2Test::class.java.getResource("/day2_input").readText()

        val result = day2_b(input)
        assertEquals(result, 428)
    }


    @Test
    fun parsePassword() {
        val (password, letter, range) = parse("10-30 a: abcde")

        assertEquals(password, "abcde")
        assertEquals(letter, "a")
        assertEquals(range, IntRange(10, 30))
    }

    @Test
    fun passwordIsValid_bySledRentalPlacePolicy() {

        val validPassword = Password("abcde", "a", IntRange(1, 3));
        assertTrue(validPassword.isValid_bySledRentalPlacePolicy())
    }

    @Test
    fun passwordIsNotValid_bySledRentalPlacePolicy() {

        val validPassword = Password("cdefg", "b", IntRange(1, 3));
        assertFalse(validPassword.isValid_bySledRentalPlacePolicy())
    }

    @Test
    fun passwordIsValid_byTobogganCorporatePolicy() {

        val validPassword = Password("abcde", "a", IntRange(1, 3));
        assertTrue(validPassword.isValid_byTobogganCorporatePolicy())
    }

    @Test
    fun passwordIsNotValid_byTobogganCorporatePolicy() {

        val validPassword1 = Password("ccccccccc", "c", IntRange(2, 9));
        assertFalse(validPassword1.isValid_byTobogganCorporatePolicy())

        val validPassword2 = Password("cdefg", "b", IntRange(1, 3));
        assertFalse(validPassword2.isValid_byTobogganCorporatePolicy())
    }
}