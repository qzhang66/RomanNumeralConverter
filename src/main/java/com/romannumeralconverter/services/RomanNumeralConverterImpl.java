package com.romannumeralconverter.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RomanNumeralConverterImpl implements RomanNumeralConverter {
    private static final String[] THOUSAND_STRS = {"", "M", "MM", "MMM"};
    private static final String[] HUNDRED_STRS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TEN_STRS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONE_STRS = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private static final Map<Character, Integer> ROMAN_LETTER_TO_NUM = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    @Override
    public int fromRomanNumeral(String romanNumeral) {
        int sum = 0;
        int length = romanNumeral.length();

        for (int i = 0; i < length; i++) {

            if (i < length - 1 &&
                    ROMAN_LETTER_TO_NUM.get(romanNumeral.charAt(i)) < ROMAN_LETTER_TO_NUM.get(romanNumeral.charAt(i + 1))) {
                sum = sum + (ROMAN_LETTER_TO_NUM.get(romanNumeral.charAt(i + 1)) - ROMAN_LETTER_TO_NUM.get(romanNumeral.charAt(i)));
                i++;
            } else {
                sum = sum + ROMAN_LETTER_TO_NUM.get(romanNumeral.charAt(i));
            }
        }
        return sum;
    }

    @Override
    public String toRomanNumeral(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Error: Roman numerals cannot be zero or negative. number=" + number);
        }
        if (number > 3999) {
            throw new IllegalArgumentException("Error: Roman numerals cannot exceed 3999.");
        }

        String thousands = THOUSAND_STRS[number / 1000];
        String hundreds = HUNDRED_STRS[(number % 1000) / 100];
        String tens = TEN_STRS[(number % 100) / 10];
        String ones = ONE_STRS[number % 10];

        return thousands + hundreds + tens + ones;
    }
}

