package com.romannumeralconverter.services;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(JUnitParamsRunner.class)
public class RomanNumeralConverterImplTest {

    RomanNumeralConverterImpl romanNumeralConverterImpl = new RomanNumeralConverterImpl();

    @Test
    @Parameters({
            "1, I",
            "2, II",
            "4, IV",
            "15, XV",
            "19, XIX",
            "20, XX",
            "37, XXXVII",
            "109, CIX",
            "446, CDXLVI",
            "700, DCC",
            "1234, MCCXXXIV",
            "2999, MMCMXCIX",
            "3000, MMM",
            "3549, MMMDXLIX",
            "3724, MMMDCCXXIV",
            "3999, MMMCMXCIX"

    })
    public void testToRomanNumeralWhenGivenValidNumbers(int number, String expectedRomanNum) {
        String actual = romanNumeralConverterImpl.toRomanNumeral(number);
        assertEquals(expectedRomanNum, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({"0", "4000"})
    public void testToRomanNumeralWhenGivenInvalidNumbers(int number) {
        romanNumeralConverterImpl.toRomanNumeral(number);
    }

    @Test
    @Parameters({
            "I, 1",
            "VIII, 8",
            "XVI, 16",
            "XXXIX, 39",
            "XCIX, 99",
            "CIX, 109",
            "CDXLVI, 446",
            "DCC, 700 ",
            "MCCXXXIV, 1234",
            "MMMCMXCIX, 3999",
    })
    public void testFromRomanNumeralWhenGivenValidRomanNum(String romanNum, int expectedNum) {
        int actual = romanNumeralConverterImpl.fromRomanNumeral(romanNum);
        assertEquals(expectedNum, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({"IIX", "MMMMM", "c", "ABCD"})
    public void testFromRomanNumeralWhenGivenInvalidRomanNum(String romanNum) {
        romanNumeralConverterImpl.fromRomanNumeral(romanNum);
    }
}
