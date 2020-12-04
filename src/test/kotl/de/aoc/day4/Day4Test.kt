package de.aoc.day4

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
    fun day4_b() {
        val input = Day4Test::class.java.getResource("/day4_input").readText()

        val result = day4_b(input)
        assertEquals(result, 145)
    }

    @Test
    fun day4_b_example_1() {
        val input = "eyr:1972 cid:100\n" +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
                "\n" +
                "iyr:2019\n" +
                "hcl:#602927 eyr:1967 hgt:170cm\n" +
                "ecl:grn pid:012533040 byr:1946\n" +
                "\n" +
                "hcl:dab227 iyr:2012\n" +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" +
                "\n" +
                "hgt:59cm ecl:zzz\n" +
                "eyr:2038 hcl:74454a iyr:2023\n" +
                "pid:3556412378 byr:2007";

        val result = day4_b(input)
        assertEquals(result, 0)
    }

    @Test
    fun day4_b_example_2() {
        val input = "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" +
                "\n" +
                "hcl:#888785\n" +
                "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                "pid:545766238 ecl:hzl\n" +
                "eyr:2022\n" +
                "\n" +
                "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719";

        val result = day4_b(input)
        assertEquals(result, 4)
    }

    @Test
    fun parse() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n";
        val passport = Passport(input)
        assertEquals(passport.ecl?.input, "gry");
        assertEquals(passport.pid?.input, "860033327");
        assertEquals(passport.hcl?.input, "#fffffd");
        assertEquals(passport.byr, 1937);
        assertEquals(passport.iyr, 2017);
        assertEquals(passport.hgt?.input, "183cm");
    }

    @Test
    fun hcl() {
        var hcl = Hcl("#123abc")
        assertTrue(hcl.isValid())

        hcl = Hcl("#123abz")
        assertFalse(hcl.isValid())

        hcl = Hcl("123abc")
        assertFalse(hcl.isValid())
    }

    @Test
    fun pid() {
        var pid = Pid("000000001")
        assertTrue(pid.isValid())

        pid = Pid("0123456789")
        assertFalse(pid.isValid())
    }

    @Test
    fun hgt() {
        var hgt = Hgt("60in")
        assertTrue(hgt.isValid())

        hgt = Hgt("190cm")
        assertTrue(hgt.isValid())

        hgt = Hgt("190in")
        assertFalse(hgt.isValid())

        hgt = Hgt("190")
        assertFalse(hgt.isValid())
    }

    @Test
    fun ecl() {
        var ecl = Ecl("brn")
        assertTrue(ecl.isValid())

        ecl = Ecl("wat")
        assertFalse(ecl.isValid())
    }
}