package de.aoc.day4

import de.aoc.day3.Day3Test
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day4Test {


    @Test
    fun day4_a() {
        val input = Day4Test::class.java.getResource("/day4_input").readText()

        val result = day4_a(input)
        assertEquals(result, 247)
    }

    @Test
    fun day4_a_example_1() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929\n" +
                "\n" +
                "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm\n" +
                "\n" +
                "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in";

        val result = day4_a(input)
        assertEquals(result, 2)
    }

    @Test
    fun parse() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n";
        assertEquals(Passport(input).components, listOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "cid", "hgt"));

    }
}